package com.xiaxinyu.OA.service;

import com.xiaxinyu.OA.dao.LeaveFormDao;
import com.xiaxinyu.OA.dao.NoticeDao;
import com.xiaxinyu.OA.dao.ProcessFlowDao;
import com.xiaxinyu.OA.entity.Employee;
import com.xiaxinyu.OA.entity.LeaveForm;
import com.xiaxinyu.OA.entity.Notice;
import com.xiaxinyu.OA.entity.ProcessFlow;
import com.xiaxinyu.OA.service.exception.BusinessException;
import com.xiaxinyu.OA.utils.MybatisUtils;
import com.xiaxinyu.OA.dao.EmployeeDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 请假单流程服务
 */
public class LeaveFormService {
    /**
     * 创建请假单
     * @param leaveForm 前端输入的请假单数据
     * @return 持久化后的请假单对象
     */
    public LeaveForm createLeaveForm(LeaveForm leaveForm){
        LeaveForm savedForm = (LeaveForm) MybatisUtils.executeUpdate(sqlSession -> {
            //1.持久化leaveForm表单数据，8级以下员工表单状态为processing，8级员工(总经理)状态为approved。
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = employeeDao.selectById(leaveForm.getEmployeeId());
            if(employee.getLevel() == 8){
                leaveForm.setState("approved");
            }else{
                leaveForm.setState("processing");
            }
            LeaveFormDao leaveFormDao = sqlSession.getMapper(LeaveFormDao.class);
            leaveFormDao.insert(leaveForm);

            //2.增加第一条流程数据，说明表单已提交，状态为complete
            ProcessFlowDao processFlowDao = sqlSession.getMapper(ProcessFlowDao.class);
            ProcessFlow flow1 = new ProcessFlow();
            flow1.setFormId(leaveForm.getFormId());
            flow1.setOperatorId(employee.getEmployeeId());
            flow1.setAction("apply");
            flow1.setCreateTime(new Date());
            flow1.setOrderNo(1);
            flow1.setState("complete");
            flow1.setIsLast(0);
            processFlowDao.insert(flow1);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH时");

            NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);

            //3.分情况创建其余流程数据
                //3.1 7级以下员工生成部门经理审批任务，若请假时间大于36小时则生成总经理审批任务
            if(employee.getLevel() < 7){
                Employee dmanager = employeeDao.selectLeader(employee);
                ProcessFlow flow2 = new ProcessFlow();
                flow2.setFormId(leaveForm.getFormId());
                flow2.setOperatorId(dmanager.getEmployeeId());
                flow2.setAction("audit");
                flow2.setCreateTime(new Date());
                flow2.setOrderNo(2);
                flow2.setState("process");
                Float  hours = (leaveForm.getEndTime().getTime() - leaveForm.getStartTime().getTime()) / 1000f / 60 / 60;
                if(hours >= BusinessConstants.MANAGER_AUDIT_HOURS){
                    flow2.setIsLast(0);
                    processFlowDao.insert(flow2);
                    Employee manager = employeeDao.selectLeader(dmanager); //得到总经理信息
                    ProcessFlow flow3 = new ProcessFlow();
                    flow3.setFormId(leaveForm.getFormId());
                    flow3.setOperatorId(manager.getEmployeeId());
                    flow3.setAction("audit");
                    flow3.setCreateTime(new Date());
                    flow3.setState("ready");
                    flow3.setOrderNo(3);
                    flow3.setIsLast(1);
                    processFlowDao.insert(flow3);
                }else{
                    flow2.setIsLast(1);
                    processFlowDao.insert(flow2);
                }
                //请假单已提交消息
                String noticeContent = String.format("您的请假申请[%s-%s]已提交，请等待上级审批",
                        sdf.format(leaveForm.getStartTime()),sdf.format(leaveForm.getEndTime()));
                noticeDao.insert(new Notice(employee.getEmployeeId(),noticeContent));

                //通知部门经理审批消息
                noticeContent = String.format("%s-%s提起请假申请[%s-%s]，请尽快审批",
                        employee.getTitle(),employee.getName(),sdf.format(leaveForm.getStartTime()),sdf.format(leaveForm.getEndTime()));

                noticeDao.insert(new Notice(dmanager.getEmployeeId(),noticeContent));

            }//3.2 7级员工生成总经理审批任务
            else if(employee.getLevel() == 7) {
                Employee manager = employeeDao.selectLeader(employee);
                ProcessFlow flow2 = new ProcessFlow();
                flow2.setFormId(leaveForm.getFormId());
                flow2.setOperatorId(manager.getEmployeeId());
                flow2.setAction("audit");
                flow2.setCreateTime(new Date());
                flow2.setState("process");
                flow2.setOrderNo(2);
                flow2.setIsLast(1);
                processFlowDao.insert(flow2);

                //请假单已提交消息
                String noticeContent = String.format("您的请假申请[%s-%s]已提交，请等待上级审批",
                        sdf.format(leaveForm.getStartTime()),sdf.format(leaveForm.getEndTime()));
                noticeDao.insert(new Notice(employee.getEmployeeId(),noticeContent));

                //通知总经理审批消息
                noticeContent = String.format("%s-%s提起请假申请[%s-%s]，请尽快审批",
                        employee.getTitle(),employee.getName(),sdf.format(leaveForm.getStartTime()),sdf.format(leaveForm.getEndTime()));

                noticeDao.insert(new Notice(manager.getEmployeeId(),noticeContent));

            }
                //3.3 8级员工生成总经理审批任务，系统自动通过
            else if(employee.getLevel() == 8){
                ProcessFlow flow2 = new ProcessFlow();
                flow2.setFormId(leaveForm.getFormId());
                flow2.setOperatorId(employee.getEmployeeId());
                flow2.setAction("audit");
                flow2.setCreateTime(new Date());
                flow2.setAuditTime(new Date());
                flow2.setResult("approved");
                flow2.setReason("自动通过");
                flow2.setState("complete");
                flow2.setOrderNo(2);
                flow2.setIsLast(1);
                processFlowDao.insert(flow2);
                String noticeContent = String.format("您的请假申请[%s-%s]系统已自动批准通过." ,
                        sdf.format(leaveForm.getStartTime()) , sdf.format(leaveForm.getEndTime()));
                noticeDao.insert(new Notice(employee.getEmployeeId(),noticeContent));
            }

            return leaveForm;
        });
        return savedForm;
    }

    /**
     * 获取指定任务状态及指定任务经办人对应的请假单列表
     * @param pfState
     * @param operatorId
     * @return
     */
    public List<Map> getLeaveFormList(String pfState, Long operatorId){
        return (List<Map>)MybatisUtils.executeQuery(sqlSession -> {
            LeaveFormDao leaveFormDao = sqlSession.getMapper(LeaveFormDao.class);
            return leaveFormDao.selectByParameters(pfState,operatorId);
        });
    }

    public void audit(Long formId,Long operatorId,String result,String reason){
        MybatisUtils.executeUpdate(sqlSession -> {
            //1.无论同意/驳回,当前任务状态变更为complete
            ProcessFlowDao processFlowDao = sqlSession.getMapper(ProcessFlowDao.class);
            List<ProcessFlow> flowList = processFlowDao.selectByFormId(formId);
            if(flowList.size() == 0){
                throw new BusinessException("PF001","无效的审批流程");
            }
            //获取当前任务的process对象
            ProcessFlow process = null;
            List<ProcessFlow> processList = flowList.stream().filter(p -> p.getOperatorId() ==  operatorId && p.getState().equals("process")).collect(Collectors.toList());
            if(processList.size() == 0){
                throw new BusinessException("PF002","未找到待处理任务");
            }else {
                process = processList.get(0);
                process.setState("complete");
                process.setResult(result);
                process.setReason(reason);
                process.setAuditTime(new Date());
                processFlowDao.update(process);
            }
            //2.如果当前任务是最后一个节点，代表流程结束，更新请假单状态为approved或refused
            LeaveFormDao leaveFormDao = sqlSession.getMapper(LeaveFormDao.class);
            LeaveForm leaveForm = leaveFormDao.selectById(formId);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH时");
            EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
            Employee employee = employeeDao.selectById(leaveForm.getEmployeeId());
            Employee operator = employeeDao.selectById(operatorId);
            NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
            if(process.getIsLast() == 1){
                leaveForm.setState(result); //approved || refused;
                leaveFormDao.update(leaveForm);

                String strResult = null;
                if(result.equals("approved")){
                    strResult = "批准";
                }else if(result.equals("refused")){
                    strResult = "驳回";
                }

                String noticeContent = String.format("您的请假申请[%s-%s]%s%s已%s,审批意见:%s,审批流程已结束",
                        sdf.format(leaveForm.getStartTime()) , sdf.format(leaveForm.getEndTime()),
                        operator.getTitle(),operator.getName(),
                        strResult,reason);//发给表单提交人的通知

                noticeDao.insert(new Notice(leaveForm.getEmployeeId(),noticeContent));

                noticeContent = String.format("%s-%s提起请假申请[%s-%s]您已%s,审批意见:%s,审批流程已结束" ,
                        employee.getTitle() , employee.getName() , sdf.format( leaveForm.getStartTime()) , sdf.format(leaveForm.getEndTime()),
                        strResult , reason);//发给审批人的通知
                noticeDao.insert(new Notice(operator.getEmployeeId(),noticeContent));

            }else{
                List<ProcessFlow> readyList = flowList.stream().filter(p -> p.getState().equals("ready")).collect(Collectors.toList());
                System.out.println(Collectors.toList());
                //3.如果当前节点不是最后一个节点，那么下一个节点的状态从ready变为process
                if(result.equals("approved")){
                    ProcessFlow readyProcess = readyList.get(0);
                    readyProcess.setState("process");
                    processFlowDao.update(readyProcess);

                    //消息1: 通知表单提交人,部门经理已经审批通过,交由上级继续审批
                    String noticeContent1 = String.format("您的请假申请[%s-%s]%s%s已批准,审批意见:%s ,请继续等待上级审批" ,
                            sdf.format(leaveForm.getStartTime()) , sdf.format(leaveForm.getEndTime()),
                            operator.getTitle() , operator.getName(),reason);
                    noticeDao.insert(new Notice(leaveForm.getEmployeeId(),noticeContent1));

                    //消息2: 通知总经理有新的审批任务
                    String noticeContent2 = String.format("%s-%s提起请假申请[%s-%s],请尽快审批" ,
                            employee.getTitle() , employee.getName() , sdf.format( leaveForm.getStartTime()) , sdf.format(leaveForm.getEndTime()));
                    noticeDao.insert(new Notice(readyProcess.getOperatorId(),noticeContent2));

                    //消息3: 通知部门经理(当前经办人),员工的申请单你已批准,交由上级继续审批
                    String noticeContent3 = String.format("%s-%s提起请假申请[%s-%s]您已批准,审批意见:%s,申请转至上级领导继续审批" ,
                            employee.getTitle() , employee.getName() , sdf.format( leaveForm.getStartTime()) , sdf.format(leaveForm.getEndTime()), reason);
                    noticeDao.insert(new Notice(operator.getEmployeeId(),noticeContent3));


                }else if(result.equals("refused")) {
                    //4.如果当前节点不是最后一个节点且审批被驳回，则后续所有任务状态变成cancel，请假单状态变为refused
                    for(ProcessFlow p : readyList){
                        p.setState("cancel");
                        processFlowDao.update(p);
                    }
                    leaveForm.setState(result);
                    leaveFormDao.update(leaveForm);

                    //消息1: 通知申请人表单已被驳回
                    String noticeContent1 = String.format("您的请假申请[%s-%s]%s%s已驳回,审批意见:%s,审批流程已结束" ,
                            sdf.format(leaveForm.getStartTime()) , sdf.format(leaveForm.getEndTime()),
                            operator.getTitle() , operator.getName(),reason);
                    noticeDao.insert(new Notice(leaveForm.getEmployeeId(),noticeContent1));

                    //消息2: 通知经办人表单"您已驳回"
                    String noticeContent2 = String.format("%s-%s提起请假申请[%s-%s]您已驳回,审批意见:%s,审批流程已结束" ,
                            employee.getTitle() , employee.getName() , sdf.format( leaveForm.getStartTime()) , sdf.format(leaveForm.getEndTime()), reason);
                    noticeDao.insert(new Notice(operator.getEmployeeId(),noticeContent2));

                }

            }
            return null;
        });
    }
}

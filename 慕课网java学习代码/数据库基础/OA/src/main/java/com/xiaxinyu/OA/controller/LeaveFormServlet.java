package com.xiaxinyu.OA.controller;

import com.alibaba.fastjson.JSON;
import com.xiaxinyu.OA.entity.LeaveForm;
import com.xiaxinyu.OA.service.LeaveFormService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.xiaxinyu.OA.entity.User;
import com.xiaxinyu.OA.utils.MybatisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@WebServlet(name = "LeaveFormServlet",urlPatterns = "/leave/*")
public class LeaveFormServlet extends HttpServlet {

    private LeaveFormService leaveFormService = new LeaveFormService();
    private Logger logger = LoggerFactory.getLogger(LeaveFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //http:localhost/leave/create
        String uri = req.getRequestURI();
        System.out.println(uri);
        String methodName = req.getRequestURI().substring(uri.lastIndexOf('/') + 1);
        if(methodName.equals("create")){
            this.create(req,resp);
        }else if(methodName.equals("list")){
            this.getLeaveFormList(req,resp);
        }else if(methodName.equals("audit")){
            this.audit(req,resp);
        }
    }

    /**
     * 创建请假单
     * @param req
     * @param resp
     * @throws IOException
     */
    private void create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("login_user");
        String formType = req.getParameter("formType");
        String strStartTime = req.getParameter("startTime");
        String strEndTime = req.getParameter("endTime");
        String reason = req.getParameter("reason");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");

        Map result = new HashMap();

        //调用业务逻辑方法
        try{
            LeaveForm leaveForm = new LeaveForm();
            leaveForm.setEmployeeId(user.getEmployeeId());
            leaveForm.setEmployeeId(user.getEmployeeId());
            leaveForm.setStartTime(sdf.parse(strStartTime));
            leaveForm.setEndTime(sdf.parse(strEndTime));
            leaveForm.setFormType(Integer.parseInt(formType));
            leaveForm.setReason(reason);
            leaveForm.setCreateTime(new Date());
            leaveFormService.createLeaveForm(leaveForm);
            result.put("code","0");
            result.put("message","success");

        }catch (Exception e){
            logger.error("请假申请异常：",e);
            result.put("code",e.getClass().getSimpleName());
            result.put("message",e.getMessage());
        }
        String json = JSON.toJSONString(result);
        resp.getWriter().println(json);
    }


    /**
     * 查询需要审核的请假单列表
     * @param req
     * @param resp
     * @throws IOException
     */
    public void getLeaveFormList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User)req.getSession().getAttribute("login_user");
        List<Map> leaveFormList = leaveFormService.getLeaveFormList("process",user.getEmployeeId());
        Map result = new HashMap<>();
        result.put("code",0);
        result.put("msg","");
        result.put("count",leaveFormList.size());
        result.put("data",leaveFormList);
        String json = JSON.toJSONString(result);


        resp.getWriter().println(json);

    }

    public void audit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String formId = req.getParameter("formId");
        String result = req.getParameter("result");
        String reason = req.getParameter("reason");

        User user = (User)req.getSession().getAttribute("login_user");
        Map mpResult = new HashMap<>();
        try{
            leaveFormService.audit(Long.parseLong(formId),user.getEmployeeId(),result,reason);
            mpResult.put("code","0");
            mpResult.put("message","success");
        }catch (Exception e){
            logger.error("请假单审核失败",e);
            mpResult.put("code",e.getClass().getSimpleName());
            mpResult.put("message",e.getMessage());
        }

        String json = JSON.toJSONString(mpResult);
        resp.getWriter().println(json);
    }

}

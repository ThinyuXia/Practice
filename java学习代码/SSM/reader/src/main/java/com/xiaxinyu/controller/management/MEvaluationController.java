package com.xiaxinyu.controller.management;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaxinyu.entity.Book;
import com.xiaxinyu.entity.Evaluation;
import com.xiaxinyu.entity.Member;
import com.xiaxinyu.mapper.BookMapper;
import com.xiaxinyu.mapper.EvaluationMapper;
import com.xiaxinyu.mapper.MemberMapper;
import com.xiaxinyu.service.EvaluationService;
import com.xiaxinyu.service.exception.BussinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/management/evaluation")
public class MEvaluationController {

    @Resource
    private EvaluationService evaluationService;

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private BookMapper bookMapper;


    @GetMapping("/evaluation.html")
    public ModelAndView showEvaluation(HttpSession session){
        if(session.getAttribute("loginUser") == null) return new ModelAndView("redirect:/management/user/login");
        return new ModelAndView("/management/evaluation");
    }

    @GetMapping("/list")
    @ResponseBody
    public Map list(Integer page,Integer limit){
        Map result = new HashMap();
        try {
            IPage<Evaluation> pageObject = evaluationService.paging(null,null,page,limit);
            List<Evaluation> evaluationList = pageObject.getRecords();
            for(Evaluation evaluation : evaluationList){
                Member m = memberMapper.selectById(evaluation.getMemberId());
                Book b = bookMapper.selectById(evaluation.getBookId());
                evaluation.setMember(m);
                evaluation.setBook(b);
            }

            pageObject.setRecords(evaluationList);
            result.put("code",0);
            result.put("msg","success");
            result.put("data",pageObject.getRecords());
            result.put("count",pageObject.getTotal());
        }catch (BussinessException ex){
            ex.printStackTrace();
            result.put("code",ex.getCode());
            result.put("msg",ex.getMsg());
        }
        return result;
    }

    @PostMapping("/disable")
    @ResponseBody
    public Map disableEvaluation(Long evaluationId,String reason){
        Map result = new HashMap();
        try{
            evaluationService.disableEvaluation(evaluationId,reason);
            result.put("code",0);
            result.put("msg","success");
        }catch (BussinessException ex){
            ex.printStackTrace();
            result.put("code",ex.getCode());
            result.put("msg",ex.getMsg());
        }
        return result;
    }
}

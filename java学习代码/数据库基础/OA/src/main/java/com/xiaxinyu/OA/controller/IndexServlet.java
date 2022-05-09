package com.xiaxinyu.OA.controller;

import com.xiaxinyu.OA.entity.Department;
import com.xiaxinyu.OA.entity.Employee;
import com.xiaxinyu.OA.entity.Node;
import com.xiaxinyu.OA.entity.User;
import com.xiaxinyu.OA.service.DepartmentService;
import com.xiaxinyu.OA.service.EmployeeService;
import com.xiaxinyu.OA.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.io.IOException;

@WebServlet(name = "IndexServlet",urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    private UserService userService = new UserService();
    private EmployeeService employeeService = new EmployeeService();
    private DepartmentService departmentService = new DepartmentService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        //得到当前登陆的用户对象以及员工对象
        User user = (User)req.getSession().getAttribute("login_user");
        //得到当前登陆的员工对象
        Employee emp = employeeService.selectById(user.getEmployeeId());
        //得到当前登陆的用户所在部门对象
        Department department = departmentService.selectById(emp.getDepartmentId());
        //获取用户可用功能模块列表
        List<Node> nodeList = userService.selectNodeByUserId(user.getUserId());
        //放入请求属性
        req.setAttribute("node_list",nodeList);

        httpSession.setAttribute("current_employee",emp);
        httpSession.setAttribute("current_department",department);
        //请求派发至ftl文件进行展示
        req.getRequestDispatcher("/index.ftl").forward(req,resp);
    }
}

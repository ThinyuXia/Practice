package com.xiaxinyu.OA.controller;

import com.xiaxinyu.OA.entity.Node;
import com.xiaxinyu.OA.entity.User;
import com.xiaxinyu.OA.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.io.IOException;

@WebServlet(name = "IndexServlet",urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    private UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("login_user");
        List<Node> nodeList = userService.selectNodeByUserId(user.getUserId());
        req.setAttribute("node_list",nodeList);
        req.getRequestDispatcher("/index.ftl").forward(req,resp);
    }
}

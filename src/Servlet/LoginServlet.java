package Servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.serviceImpl;
import userMessage.userDB;
@WebServlet("/Login")
public class LoginServlet extends ViewBaseServlet{

    private serviceImpl handle=new serviceImpl();
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
      /*  req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");*/
        String username1=req.getParameter("username");
        String password1=req.getParameter("password");
        boolean rememberMe = "true".equals(req.getParameter("rememberMe"));
        System.out.println(username1+":"+password1+"是否自动登录:"+rememberMe);

        if(handle.login(new userDB(username1,password1))!=null) {//调用处理函数
            if (rememberMe) {//<--自动登录选项
                Cookie cookie = new Cookie("autologin", username1);//选择自动登录，创建cookie
                cookie.setMaxAge(7 * 24 * 60 * 60);
                resp.addCookie(cookie);
            }

            req.setAttribute("username",username1);
            super.processTemplate("success",req,resp);
        }else {
            System.out.println("登录失败");

            req.setAttribute("ErrorMessage","用户名或密码错误");
            super.processTemplate("Login", req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.processTemplate("Login",req,resp);
    }
}

package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.serviceImpl;
import service.userService;
import userMessage.userDB;
@WebServlet("/register")
public class registServlet extends ViewBaseServlet{//原本是继承HttpServlet，但加入thymeleaf后继承ViewBaseServlet，因为其也是继承自HttpServlet,故无影响
    private userService handle=new serviceImpl();
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//			System.out.println("进入register的dopost方法");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String recommit=req.getParameter("recommit");
		System.out.println("调用register");

		if(!handle.exist(username)&&password.equals(recommit)){
			userDB newUser=new userDB(username,password);
			handle.registUser(newUser);

			System.out.println("注册载入数据库成功");
		   req.getRequestDispatcher("/Login.html").forward(req, resp);//注册成功，跳到登录页面

		}else{//注册失败，跳回注册页面
			System.out.println("注册失败");
			req.setAttribute("ErrorMessage", "用户名已存在或两次输入密码不一致!");//【保存并回显信息】

			super.processTemplate("register", req, resp);
		}
    

	}
	
	
}

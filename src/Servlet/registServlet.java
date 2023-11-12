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
public class registServlet extends ViewBaseServlet{//ԭ���Ǽ̳�HttpServlet��������thymeleaf��̳�ViewBaseServlet����Ϊ��Ҳ�Ǽ̳���HttpServlet,����Ӱ��
    private userService handle=new serviceImpl();
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//			System.out.println("����register��dopost����");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String recommit=req.getParameter("recommit");
		System.out.println("����register");

		if(!handle.exist(username)&&password.equals(recommit)){
			userDB newUser=new userDB(username,password);
			handle.registUser(newUser);

			System.out.println("ע���������ݿ�ɹ�");
		   req.getRequestDispatcher("/Login.html").forward(req, resp);//ע��ɹ���������¼ҳ��

		}else{//ע��ʧ�ܣ�����ע��ҳ��
			System.out.println("ע��ʧ��");
			req.setAttribute("ErrorMessage", "�û����Ѵ��ڻ������������벻һ��!");//�����沢������Ϣ��

			super.processTemplate("register", req, resp);
		}
    

	}
	
	
}

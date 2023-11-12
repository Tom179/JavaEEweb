package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.bookDaoImpl;
import userMessage.bookDB;

@WebServlet("/search")
public class SearchServlet extends ViewBaseServlet{
	bookDaoImpl dao=new bookDaoImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	/*	req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");*/

		String SearchAim=req.getParameter("aim");

		HttpSession session=req.getSession();

		List<bookDB> aimBook=dao.queryAllBookByKey(bookDB.class,SearchAim);
		
		session.setAttribute("AimBook",aimBook);
		session.setAttribute("SearchAim",SearchAim);
		//System.out.println(aimBook.size());
		
		if(aimBook.size()==0)
			session.setAttribute("isSearched","什么都没找到啊T_T");
		
		super.processTemplate("book", req, resp);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		// TODO Auto-generated method stub
      
		HttpSession session=req.getSession();
		session.setAttribute("SearchAim","");//搜索框内容
		session.setAttribute("isSearched","");//搜索提示
		session.setAttribute("AimBook",dao.queryAllBookByKey(bookDB.class," "));//搜索结果
	 
		super.processTemplate("图书管理页面", req, resp);
	  
	}
	
}

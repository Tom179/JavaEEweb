package Servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.bookDaoImpl;
import userMessage.bookDB;

@WebServlet("/bookServlet")//【可以通过session.setAttribute()的方式在前端创建变量哦,  如页码pageNo,或者输入框内已有的错误信息等】
public class BookServlet extends ViewBaseServlet{
public static int pageNo=1;//页码
	bookDaoImpl bookDao=new bookDaoImpl();
	
	@Override//表单查询展示
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session=req.getSession();
		//session.setAttribute("bookList",bookDao.queryAllBook(bookDB.class));//查询到的列表所有，并保存到作用域中

		String pageNoStr=req.getParameter("pageNo");//session作用域中数据用req获取
		if(pageNoStr!=null) {
			pageNo=Integer.parseInt(pageNoStr);
		}session.setAttribute("pageNo",pageNo);//保存页码数


	session.setAttribute("bookList",bookDao.queryOnePage(bookDB.class,pageNo));//在session中保存列表(传入查询页码)
    int countPage=bookDao.countPage();
    session.setAttribute("countPage",countPage);//把总页数保存到session作用域中
		
		super.processTemplate("book", req, resp);
	}

	@Override//表单提交接收
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String bookName=req.getParameter("bookname");
		String author=req.getParameter("author");
		String ID=req.getParameter("ID");
		String remain=req.getParameter("remain");
        //获取参数
		bookDao.addBook(bookName, author, ID, remain);//载入数据库
		
		////////////////////////////
		resp.sendRedirect("bookServlet");//请求重定向
	}
	
}

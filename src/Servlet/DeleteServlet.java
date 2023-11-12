package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.bookDaoImpl;
import userMessage.bookDB;

@WebServlet("/del.do")
public class DeleteServlet extends ViewBaseServlet{

	 @Override //request中携带了表单中接收到${oneBook.ID}动态查询到的ID值,可直接连接数据库进行删除操作
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     bookDaoImpl Book=new bookDaoImpl();
		 System.out.println("成功进入到delete函数,要删除的id为:"+req.getParameter("ID"));
     Book.deleteBook(req.getParameter("ID"));
       
        int pageNo=1;String pageNoStr=req.getParameter("pageNo");//session作用域中数据用req获取
		if(pageNoStr!=null) {
			pageNo=Integer.parseInt(pageNoStr);
		}

      bookDaoImpl bookDao=new bookDaoImpl();
      HttpSession session=req.getSession();
      session.setAttribute("bookList", bookDao.queryOnePage(bookDB.class, pageNo));

      super.processTemplate("book", req, resp);//Template渲染方法无法更新数据?这种是请求转发?
	  //resp.sendRedirect("图书管理页面");  //不行，说什么重定向次数过多
	}    
	
}

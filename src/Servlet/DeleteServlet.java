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

	 @Override //request��Я���˱��н��յ�${oneBook.ID}��̬��ѯ����IDֵ,��ֱ���������ݿ����ɾ������
	 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     bookDaoImpl Book=new bookDaoImpl();
		 System.out.println("�ɹ����뵽delete����,Ҫɾ����idΪ:"+req.getParameter("ID"));
     Book.deleteBook(req.getParameter("ID"));
       
        int pageNo=1;String pageNoStr=req.getParameter("pageNo");//session��������������req��ȡ
		if(pageNoStr!=null) {
			pageNo=Integer.parseInt(pageNoStr);
		}

      bookDaoImpl bookDao=new bookDaoImpl();
      HttpSession session=req.getSession();
      session.setAttribute("bookList", bookDao.queryOnePage(bookDB.class, pageNo));

      super.processTemplate("book", req, resp);//Template��Ⱦ�����޷���������?����������ת��?
	  //resp.sendRedirect("ͼ�����ҳ��");  //���У�˵ʲô�ض����������
	}    
	
}

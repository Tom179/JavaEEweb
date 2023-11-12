package Servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.bookDaoImpl;
import userMessage.bookDB;

@WebServlet("/bookServlet")//������ͨ��session.setAttribute()�ķ�ʽ��ǰ�˴�������Ŷ,  ��ҳ��pageNo,��������������еĴ�����Ϣ�ȡ�
public class BookServlet extends ViewBaseServlet{
public static int pageNo=1;//ҳ��
	bookDaoImpl bookDao=new bookDaoImpl();
	
	@Override//����ѯչʾ
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session=req.getSession();
		//session.setAttribute("bookList",bookDao.queryAllBook(bookDB.class));//��ѯ�����б����У������浽��������

		String pageNoStr=req.getParameter("pageNo");//session��������������req��ȡ
		if(pageNoStr!=null) {
			pageNo=Integer.parseInt(pageNoStr);
		}session.setAttribute("pageNo",pageNo);//����ҳ����


	session.setAttribute("bookList",bookDao.queryOnePage(bookDB.class,pageNo));//��session�б����б�(�����ѯҳ��)
    int countPage=bookDao.countPage();
    session.setAttribute("countPage",countPage);//����ҳ�����浽session��������
		
		super.processTemplate("book", req, resp);
	}

	@Override//���ύ����
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String bookName=req.getParameter("bookname");
		String author=req.getParameter("author");
		String ID=req.getParameter("ID");
		String remain=req.getParameter("remain");
        //��ȡ����
		bookDao.addBook(bookName, author, ID, remain);//�������ݿ�
		
		////////////////////////////
		resp.sendRedirect("bookServlet");//�����ض���
	}
	
}

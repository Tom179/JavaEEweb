package DAO;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;


public class bookDaoImpl extends BaseDao{

	public void addBook(String bookName,String author,String ID,String remain){
		update("insert into books values(?,?,?,?)",bookName,author,ID,remain);
	}
	
	public void deleteBook(String ID){//删除
		update("delete from books where ID=?",ID);
		
	}
	

	
	public <T> List<T> queryAllBook(Class<T> type) {
		String sql="select* from books";
		
		return queryForList(type,sql);
	}
	
	public <T> List<T> queryAllBookByKey(Class<T> type,String key) {
		String sql="select* from books where bookname like ?";
		
		return queryForList(type,sql,"%"+key+"%");
	}
	
	
	public <T> List<T> queryOnePage(Class<T> type,int page){
		
		String sql="select* from books limit ?,10";//此sql语句为从第几个的(下一个)开始，查询几个
		
		
		int start=(page-1)*10;
		
		return queryForList(type,sql,start);
	}
	
	
		
	
	public int countPage(){
		int count=(countAllBook()+10-1)/10;
		
		return count;
	}	
	
	
	
	
}

package userMessage;

import java.io.Serializable;

public class bookDB implements Serializable{

	private static final long serialVersionUID = 1L;
	String bookName;
	String author;
	String ID;
	int remain;
	
	public bookDB() {}
	public bookDB(String bookName,String author,String ID,int remain) {
		this.bookName=bookName;this.author=author;
		this.ID=ID; this.remain=remain;
	}
	
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public int getRemain() {
		return remain;
	}
	public void setRemain(int remain) {
		this.remain = remain;
	}
	public String toString() {
		return "书名:"+bookName+" 作者:"+author+" ID:"+ID+" 库存:"+remain;
	}
	

}

 package DAO;

import userMessage.userDB;

public interface userDao {
	
	public userDB queryByName(String userName);//�ɾͷ��ظ��û���û�оͷ���null
	
	public userDB queryByNameAndPassword(String userName,String password);
	
	public int saveUser(userDB user);
	
	

}

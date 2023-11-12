 package DAO;

import userMessage.userDB;

public interface userDao {
	
	public userDB queryByName(String userName);//由就返回该用户，没有就返回null
	
	public userDB queryByNameAndPassword(String userName,String password);
	
	public int saveUser(userDB user);
	
	

}

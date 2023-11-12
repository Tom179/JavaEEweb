package service;

import userMessage.userDB;

public interface userService {

	public  void registUser(userDB user);//注册方法
	
    public userDB login(userDB user);//登录方法
    
    public boolean exist(String userName);//验证用户是否已经存在方法
 
}

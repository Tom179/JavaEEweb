package service;

import userMessage.userDB;

public interface userService {

	public  void registUser(userDB user);//ע�᷽��
	
    public userDB login(userDB user);//��¼����
    
    public boolean exist(String userName);//��֤�û��Ƿ��Ѿ����ڷ���
 
}

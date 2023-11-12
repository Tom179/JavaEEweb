package DAO;

import userMessage.userDB;
//�����ݿ�����ࡿֱ��
public class UserDaoImpl extends BaseDao {       //UserDao���̳�BaseDao�е����ݿ����ɾ�Ĳ�
	 
	public userDB queryByName(String userName) {
		// TODO Auto-generated method stub
		String sql="select* from users where username = ?";
		return queryForOne(userDB.class,sql,userName);
		
	}
    
	public userDB queryByNameAndPassword(String userName, String password) {
		// TODO Auto-generated method stub
		String sql="select* from users where username = ? and password = ?";
				
		return queryForOne(userDB.class,sql,userName,password);
	}
	
	public  int saveUser(userDB user) {
		// TODO Auto-generated method stub
		String sql="insert into users(`username`,`password`)values(?,?)";//`���غŲ�Ҫд�ɵ�����
		
		return update(sql,user.getUsername(),user.getPassword());
	}

	

}

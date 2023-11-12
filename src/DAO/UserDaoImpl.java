package DAO;

import userMessage.userDB;
//【数据库操作类】直接
public class UserDaoImpl extends BaseDao {       //UserDao，继承BaseDao中的数据库的增删改查
	 
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
		String sql="insert into users(`username`,`password`)values(?,?)";//`着重号不要写成单引号
		
		return update(sql,user.getUsername(),user.getPassword());
	}

	

}

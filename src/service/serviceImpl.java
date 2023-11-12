package service;

import DAO.UserDaoImpl;
import userMessage.userDB;

public class serviceImpl implements userService {//【业务类】直接

	 
	UserDaoImpl dao=new UserDaoImpl();
	@Override
	public void registUser(userDB user) {//注册
		// TODO Auto-generated method stub
		dao.saveUser(user);
		
	}

	@Override
	public userDB login(userDB user) {//登录，也就是查询数据库中是否存在
		// TODO Auto-generated method stub
		return dao.queryByNameAndPassword(user.getUsername(),user.getPassword());
	}

	@Override
	public boolean exist(String userName){//检查用户是否存在
		// TODO Auto-generated method stub
		if(dao.queryByName(userName)==null)
		return false;
		
			return true;
	}

}

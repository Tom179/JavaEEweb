package service;

import DAO.UserDaoImpl;
import userMessage.userDB;

public class serviceImpl implements userService {//��ҵ���ࡿֱ��

	 
	UserDaoImpl dao=new UserDaoImpl();
	@Override
	public void registUser(userDB user) {//ע��
		// TODO Auto-generated method stub
		dao.saveUser(user);
		
	}

	@Override
	public userDB login(userDB user) {//��¼��Ҳ���ǲ�ѯ���ݿ����Ƿ����
		// TODO Auto-generated method stub
		return dao.queryByNameAndPassword(user.getUsername(),user.getPassword());
	}

	@Override
	public boolean exist(String userName){//����û��Ƿ����
		// TODO Auto-generated method stub
		if(dao.queryByName(userName)==null)
		return false;
		
			return true;
	}

}

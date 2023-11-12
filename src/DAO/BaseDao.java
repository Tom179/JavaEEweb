package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import Utils.jdbcUtils;





public abstract class BaseDao {
	//��ɾ�Ĳ����ֳ�dbUtils��QueryRunner��
		private QueryRunner queryRunner=new QueryRunner(); 
		
		
		public int update(String sql,Object...args) {//����sql��䣬Ԥ����ռλ��
			Connection conn=jdbcUtils.getConnection();
			 
			try { 
				return queryRunner.update(conn, sql, args);//�����ܵ�Ӱ�������
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				jdbcUtils.close(conn);
			}
			
			
			return -1;
		}
		
		//��ѯһ����¼��beanHandler����ѯ������¼beanListHandler//<T>��ѯ�ͷ������͵ķ���
		
		public <T>T queryForOne(Class<T> type ,String sql,Object...args) {//��ѯ����һ������:javaBean
	    Connection conn=jdbcUtils.getConnection();
	    try {
			
		           
		 T aim = queryRunner.query(conn, sql,new BeanHandler<T>(type),args);
	     System.out.println(aim);//����̨�����ѯ���
	     return aim;
	     
	     
	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}finally {
		 jdbcUtils.close(conn);
	}

	    
	    
		return null;
		}
		
		
		public <T>List<T> queryForList(Class<T> type,String sql,Object...args){//��ѯ���ض������:javaBean
			 Connection conn=jdbcUtils.getConnection();
			 try {
			 		
			 	           
			 	 List<T> aim = queryRunner.query(conn, sql,new BeanListHandler<T>(type),args);//////
			      //System.out.println(aim);
			      return aim;
			      
			      
			     
			 } catch (SQLException e) {
			 		// TODO Auto-generated catch block
			 		e.printStackTrace();
			 }finally {
			 	 jdbcUtils.close(conn);
			 }

	   
			 	return null;
		}
		
		
		
		public int countAllBook() {//�޸�Ϊcountall��������Ϊ��������
			int count =0;
			Connection conn=jdbcUtils.getConnection();
			ResultSet rs;
			        try {
			        	
			        	Statement stmt=conn.createStatement();
					rs=stmt.executeQuery("select * from books");
					 while(rs.next())
					 count++;
						
					
			        }
			        catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						jdbcUtils.close(conn);
						
					}
			//System.out.println(count);
			return count;
		}
		
}

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
	//增删改查用现成dbUtils的QueryRunner类
		private QueryRunner queryRunner=new QueryRunner(); 
		
		
		public int update(String sql,Object...args) {//传入sql语句，预编译占位符
			Connection conn=jdbcUtils.getConnection();
			 
			try { 
				return queryRunner.update(conn, sql, args);//返回受到影响的行数
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				jdbcUtils.close(conn);
			}
			
			
			return -1;
		}
		
		//查询一条记录用beanHandler，查询多条记录beanListHandler//<T>查询和返回类型的泛型
		
		public <T>T queryForOne(Class<T> type ,String sql,Object...args) {//查询返回一个对象:javaBean
	    Connection conn=jdbcUtils.getConnection();
	    try {
			
		           
		 T aim = queryRunner.query(conn, sql,new BeanHandler<T>(type),args);
	     System.out.println(aim);//控制台输出查询结果
	     return aim;
	     
	     
	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}finally {
		 jdbcUtils.close(conn);
	}

	    
	    
		return null;
		}
		
		
		public <T>List<T> queryForList(Class<T> type,String sql,Object...args){//查询返回多个对象:javaBean
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
		
		
		
		public int countAllBook() {//修改为countall，表名作为参数传入
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

package Utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;



public class jdbcUtils {
	private static DataSource source;
	static{//一定要加static关键字，静态代码块
		     Properties pro=new Properties(); 
		     InputStream is =jdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");//读取配置文件
	
		    	try {
				
		    	pro.load(is);
					
				source=DruidDataSourceFactory.createDataSource(pro);//创建连接池
				
				System.out.println(source.getConnection());
				}
		    	
				 catch (Exception e) { 
									// TODO Auto-generated catch block
									e.printStackTrace();
				 }
				
    
		
	}//用静态代码块初始化数据库连接池


	
	
	public static Connection getConnection() {
	
			Connection conn=null;
			try {
					conn = source.getConnection();
			}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}//从连接池获取连接
        
    return conn;
	}
	
	public static void close(Connection conn){
		if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			    System.out.println("资源关闭异常");
				e.printStackTrace();
			}
		
	}
}

package Utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;



public class jdbcUtils {
	private static DataSource source;
	static{//һ��Ҫ��static�ؼ��֣���̬�����
		     Properties pro=new Properties(); 
		     InputStream is =jdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");//��ȡ�����ļ�
	
		    	try {
				
		    	pro.load(is);
					
				source=DruidDataSourceFactory.createDataSource(pro);//�������ӳ�
				
				System.out.println(source.getConnection());
				}
		    	
				 catch (Exception e) { 
									// TODO Auto-generated catch block
									e.printStackTrace();
				 }
				
    
		
	}//�þ�̬������ʼ�����ݿ����ӳ�


	
	
	public static Connection getConnection() {
	
			Connection conn=null;
			try {
					conn = source.getConnection();
			}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}//�����ӳػ�ȡ����
        
    return conn;
	}
	
	public static void close(Connection conn){
		if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			    System.out.println("��Դ�ر��쳣");
				e.printStackTrace();
			}
		
	}
}

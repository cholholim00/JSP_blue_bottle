package bluebottle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

public class JDBConnect {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	
	public JDBConnect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");

			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "musthave";
			String pwd = "1234";
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(기본 생성자)");
			
		 } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	
	public JDBConnect(String url, String id, String pwd, String driver) {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, id, pwd);
            System.out.println("DB 연결 성공(인수 생성자 1)");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
        
        
        public JDBConnect(ServletContext application) {
        	try {
        		String driver = application.getInitParameter("OracleDriver");
        		Class.forName(driver);
        		
        		String url = application.getInitParameter("OracleURL");
        		String id = application.getInitParameter("OracleId");
        		String pwd = application.getInitParameter("OraclePwd");
        		con = DriverManager.getConnection(url, id, pwd);
        		
        		System.out.println("DB 연결 성공(인수 생성자 2)");
        	}catch (Exception e) {
                e.printStackTrace();
            }
        }
  

    public void close() {
        try {
            if (rs != null) rs.close();
            if (psmt != null) psmt.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
            
            System.out.println("JDBC 자원 해제");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	}
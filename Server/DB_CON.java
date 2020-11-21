package qq_server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_CON {
	public Connection con=null;
	private String user="peng";
	private String psd="123456";
	private String url="jdbc:sqlserver://localhost:1433;DatabaseName=qq";
	public Connection getcon(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("数据库驱动加载成功");
			this.con=DriverManager.getConnection(url,user,psd);
			System.out.println("数据库链接成功");
		} 
		catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			System.out.println("数据库驱动加载失败");
			e.printStackTrace();
		}
		catch(SQLException e) {
			System.out.println("数据库链接失败");
		
		}
		return con;
	}
	/*public static void main(String args[]) {
		DB_CON db=new DB_CON();
		db.getcon();
	}*/

}

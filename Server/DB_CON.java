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
			System.out.println("���ݿ��������سɹ�");
			this.con=DriverManager.getConnection(url,user,psd);
			System.out.println("���ݿ����ӳɹ�");
		} 
		catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			System.out.println("���ݿ���������ʧ��");
			e.printStackTrace();
		}
		catch(SQLException e) {
			System.out.println("���ݿ�����ʧ��");
		
		}
		return con;
	}
	/*public static void main(String args[]) {
		DB_CON db=new DB_CON();
		db.getcon();
	}*/

}

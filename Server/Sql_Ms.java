package qq_server;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import resource.Chat_Info;
import resource.User_Info;

public class Sql_Ms {
	DB_CON db=null;
	public User_Info user_login(User_Info us) {
		db=new DB_CON();	
		String login="Select * from user_info where  "
				+ "user_id=? and user_psd=?";
		String revamp_state="update user_info set user_state=  1 where user_id=?";
		Connection conn=db.getcon();

		ResultSet rs;
		User_Info user_info=null;
		try{
			PreparedStatement ps=conn.prepareStatement(revamp_state);
			ps.setString(1, us.ID);
			ps.executeUpdate();
			
			ps=conn.prepareStatement(login);
			ps.setString(1, us.ID);
			ps.setString(2, us.Psd);
			
			rs=ps.executeQuery();

			while(rs.next()) {								

					user_info=new User_Info(rs.getString(1), rs.getString(2), rs.getString(3), 
							rs.getString(4), rs.getString(5), rs.getString(8),rs.getString(9));	
					return user_info;									
				}			
				rs.close();
				ps.close();
				conn.close();
			
			}	
		catch(SQLException e) {
			e.printStackTrace();
		}
		return user_info;
	}
	public void regsiter(User_Info us) {
		db =new DB_CON();
		Connection con=db.getcon();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="insert into user_info (user_id,user_psd,user_name,user_sex,user_icon) value(?,?,?,?,?)";
		try {
			ps=con.prepareStatement(sql);
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	
			
	}
	public Vector<User_Info> gain_friends_relation(User_Info us) {
		db =new DB_CON();
		Connection con=db.getcon();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Vector<User_Info> friends_info=new Vector<User_Info>();
		List<User_Info> friends_relation=new ArrayList<User_Info>();
		String gain_relation="Select * from friend_info where "
				+ "user_id=?";
		String gain_friend_information="Select * from user_info where "
				+ "user_id=?";
		try {
			//找出 用户好友，并存入Vector
			ps=con.prepareStatement(gain_relation);
			ps.setString(1, us.ID);
			rs=ps.executeQuery();

			while(rs.next()) {						
				friends_relation.add(new User_Info(rs.getString(2),rs.getString(3),
				rs.getString(4)));															
				}	
			//取出好友关系列表中的用户，并且循环获取好友信息
			for(int i=0;i<friends_relation.size();i++) {
					ps=con.prepareStatement(gain_friend_information);	
					ps.setString(1,friends_relation.get(i).ID);	
					rs=ps.executeQuery();
					while(rs.next()) {
						friends_info.add(new User_Info(rs.getString(1),rs.getString(3),
								rs.getString(4),rs.getString(5),rs.getString(9),rs.getString(8)));
						
					}
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return friends_info;
	}

	public void user_del(User_Info us) {
		db =new DB_CON();
		Connection con=db.getcon();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="Select * from user_info where "
				+ "user_id=? and user_psd=?";
	}
	public void user_revamp(User_Info us) {
		db =new DB_CON();
		Connection con=db.getcon();
		PreparedStatement ps=null;;
		ResultSet rs=null;
		String sql="Select * from user_info where "
				+ "user_id=? and user_psd=?";
	}
	public void user_addfriend(User_Info us1,User_Info us2) {
		db =new DB_CON();
		Connection con=db.getcon();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="Select * from user_info where "
				+ "user_id=? ";
	}
	public void friend_seek(User_Info us1) {
		db =new DB_CON();
		Connection con=db.getcon();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="Select * from user_info where "
				+ "user_id=? ";
		
	}
	public void User_chat(Chat_Info chat_info) {
		db=new DB_CON();
		Connection con=db.getcon();
		PreparedStatement ps=null;
		ResultSet rs=null;

		String  store_chat_info="Insert into chat_info (user_send,user_receive,datetime,chat_message) "
				+ "VALUES (?,?,?,?) ";
		System.out.println(chat_info.sender+chat_info.message+"正在准备SQL语句");
		try {
			//存储信息
			ps=con.prepareStatement(store_chat_info);
			ps.setString(1, chat_info.sender);
			ps.setString(2, chat_info.receiver);
			ps.setString(3, chat_info.datatime);
			ps.setString(4, chat_info.message);
			System.out.println(chat_info.sender+chat_info.message+"正在准备SQL语句");
			ps.executeUpdate();
			//rs=ps.executeQuery();
			System.out.println(chat_info.sender+chat_info.message+"正在准备SQL语句");
			//ps.executeUpdate();
			System.out.println("聊天记录已保存");
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块	
		}
	}
	public Vector<Chat_Info> gain_chat_history(Chat_Info ci) {
		db=new DB_CON();
		Connection con=db.getcon();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Vector<Chat_Info> chat_history =new Vector<Chat_Info>();
		String serch_message_history="Select * from chat_info where (user_send=? and user_receive=?) or"
				+ " (user_send=? and user_receive=?)";
		try {
			System.out.println("查找历史记录");
			//存储信息
			ps=con.prepareStatement(serch_message_history);
			ps.setString(1, ci.sender);
			ps.setString(2, ci.receiver);
			ps.setString(3, ci.receiver);
			ps.setString(4, ci.sender);
			rs=ps.executeQuery();
			while(rs.next()) {
				chat_history.add(new Chat_Info(rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(4)));		
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块	
		}
		return chat_history;
	}


}

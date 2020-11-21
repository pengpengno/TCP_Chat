package qq_client;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import resource.Chat_Info;
import resource.Client_Info;
import resource.Communication_Action;
import resource.User_Info;

public class Client_FunctionCenter {

	public Client_Info client_info=null;

	public boolean user_login(String id,String psd) {
		//判断用户是否输入个人账户信息
		if(!(id.equals(""))  &  !(psd.equals(""))) {
			User_Info login_Info=new User_Info(id,psd,Communication_Action.user_login);
			Client_DataTran client_dt=new Client_DataTran(login_Info);
			client_dt.User_DataTran_send();
	        System.out.println("正在核对身份信息");
	        if(client_dt.us_receive==null) {
	        	System.out.println("密码错误");
	        	return false;
	        }
	        else {
	        	System.out.println("身份确认，欢迎用户:"+client_dt.us_receive.NickName);
	        		        	
	        	
	        	Client_DataTran client_gain_fri=new Client_DataTran(new User_Info(id,psd,Communication_Action.gain_frineds));
	        	client_gain_fri.User_DataTran_send();
	        	this.client_info=new Client_Info(client_dt.us_receive,client_gain_fri.ve_us_receive);
	        	System.out.println(client_dt.us_receive.ID+"客户端信息初始化完毕"+client_gain_fri.ve_us_receive.get(0).ID);
	        	
	        	return true;
	        	}
	        }
		else {
			JOptionPane.showMessageDialog(null, "格式错误，请完整输入账号密码");
			return false;
		}
			
	}
	
	public void chat_info_send(Chat_Info chat_info) {
		Client_DataTran cidt_send=new Client_DataTran(chat_info);
		new Thread(cidt_send).start();
	}
	public void chat_info_receive(Chat_Info chat_info) {
		Client_DataTran cidt_receive=new Client_DataTran(chat_info);
		new Thread(cidt_receive).start();
	}
	public Vector gain_message_history(Chat_Info chat_info) {	
			Client_DataTran cidt_gain=new Client_DataTran(chat_info);
			cidt_gain.Chat_DataTran_send();			
			System.out.println("聊天记录已接收完毕");
		return cidt_gain.ve_ci_receive;
	}
	public void user_regsiter() {
		
		
		
	}
	public void user_off_line() {
		
	}
	
	public   void  user_del() {
		
	}
	
	public void add_friends() {
		
		
	}
	

}

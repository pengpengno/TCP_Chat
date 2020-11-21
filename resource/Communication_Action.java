package resource;

public interface Communication_Action {
		//客户端与服务器通讯协议
	final public static String user_login="1";
	final public static String user_del="2";
	final public static String user_revamp="3";
	final public static String user_seek="4";
	final public static String user_register="5";
	final public static String user_addfriend="6";
	final public static String gain_frineds="7";
	
	final public static String user_chat="8";
	final public static String user_gainchat_history="9";
	final public static String keep_receive="10";
	final public static String keep_socket="11";
	
	public static final int ONLINE=1;
	public static final int OFFLINE=2;
	public static final int HIDING=3;
	
}

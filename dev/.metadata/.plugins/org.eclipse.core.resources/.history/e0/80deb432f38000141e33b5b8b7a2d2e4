package dev.rug.shyhi;

import java.util.ArrayList;
import java.util.UUID;

import android.util.Log;

public class Convo {

	private String _id;
	private String _rev;
	private String user1;
	private String user2;
	private ArrayList<Message> messages;
	
	
	public Convo(){};
	public Convo(String i, String u1, String u2, ArrayList<Message> m){
		_id = i;
		_rev = "";
		user1 = u1;
		user2 = u2;
		messages = m;
	}
	public Convo(String cId, String u1, String u2){
		_id = cId;
		_rev = "";
		user1 = u1;
		user2 = u2;
		messages = new ArrayList<Message>();
	}
	public Convo(String i, String r, String u1, String u2, ArrayList<Message> m){
		_id = i;
		_rev = r;
		user1 = u1;
		user2 = u2;
		messages = m;
	}
	
	public String getId(){
		return _id;
	}
	public String getRev(){
		return _rev;
	}
	public String getUser1(){
		return user1;
	}
	public String getUser2(){
		return user2;
	}
	public String getOtherUser(String user){
		if(getUser1().equals(user)){
			return getUser2();
		}
		else{
			return getUser1();
		}
	}
	public Boolean hasMessages(){
		if(messages.isEmpty())
			return false;
		else
			return true;
	}
	public ArrayList<Message> getMessages(){
			return messages;
	}
	
	public void addMessage(Message m){
		messages.add(m);
	}
	
	public String getMostRecentMessage(){
		if(hasMessages())
			return messages.get(messages.size()-1).getMessage();
		else
			return "";
	}
	
	public String getMostRecentTime(){
		if(hasMessages())
			return messages.get(messages.size()-1).getTimestamp();
		else
			return "";
	}
	public String getMsgsStr(){
		ArrayList<Message> msgs = getMessages();
		String retStr = "";
		for(int i = 0; i < msgs.size(); ++i){
			if(i == msgs.size()-1)
				retStr = retStr + msgs.get(i).messageToString();
			else
				retStr = retStr + msgs.get(i).messageToString() +",";
		
		}
		return retStr;
	}
	public void setRev(String rev){
		_rev = "\""+rev+"\""; 
	}
	public String toStringForPut(){
		String convoStr = "{"+"\"_id\":"+getId()+","+
				"\"_rev\":"+getRev()+","+
				"\"type\":"+"\"convo\","+
				"\"user1\":"+getUser1()+","+
				"\"user2\":"+getUser2()+","+
				"\"messages\": ["+getMsgsStr()+"]}";
		return convoStr;				
	}
	
}

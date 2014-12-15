package dev.rug.shyhi;

import java.security.Timestamp;
import java.util.UUID;
import com.google.gson.annotations.SerializedName;

public class Message {
	
	@SerializedName("to")
	private String toID;
	@SerializedName("from")
	private String fromID;
	@SerializedName("timestamp")
	private String msgTimestamp;
	@SerializedName("message")
	private String message;
	
	public Message(){};
	public Message(String to, String from, String ts,String msg){
		toID = to;
		fromID = from;
		msgTimestamp = ts;
		message = msg;
	}
	
	public String getToID(){
		return toID;
	}
	public String getFromID(){
		return fromID;
	}
	public String getTimestamp(){
		return msgTimestamp;
	}
	public String getMessage(){
		return message;
	}
	public String getMessageNoQuotes(){
		if(!message.isEmpty())
			return message.substring(1,message.length()-1);
		else
			return "";
	
	}
	public void setToID(String t){
		toID = t;
	}
	public void setFromID(String f){
		fromID = f;
	}
	public void setMessage(String msg){
		message = msg;
	}
	
	public String messageToString(){
		String msgStr = "{\"from\": "+getFromID()+","+
						"\"to\": "+getToID()+","+
						"\"message\": "+getMessage()+","+
						"\"timestamp\":"+getTimestamp()+"}";
		return msgStr;
	}
}

package dev.rug.shyhi;

import java.util.UUID;

public class User {
	
	private String _id;
	private String _rev;
	private String latitude;
	private String longitude;
	
	public User(){
		//do something
	};
	public User(String u, String la, String lo){
		_id = u;
		latitude = la;
		longitude = lo;
		_rev = "";
	}
	public User(String u, String r, String la, String lo){
		_id = u;
		latitude = la;
		longitude = lo;
	}
	
	public String getID(){
		return _id;
	}
	public String getRev(){
		return _rev;
	}
	public String getLat(){
		return latitude;
	}
	
	public String getLong(){
		return longitude;
	}
	
	public void setLat(String l){
		latitude = l;
	}
	public void setLong(String l){
		longitude = l;
	}
	public void setID(String u){
		_id = u;
	}
	public String getUserForPost(){
		String userStr = "{"+"\"_id\":"+getID()+","+
			"\"type\":"+"\"user\","+
			"\"latitude\":"+getLat()+","+
			"\"longitude\":"+getLong()+"}";
	return userStr;				
}
	
}

package dev.rug.shyhi;

import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ConvoActivity extends ActionBarActivity {

    public static final String PREFS_NAME = "userInfoPrefs";
    public String restUrl = "http://104.236.22.60:5984/shyhi/_design/conversation/_view/get_convo?key=";
	RestUtils restUtil = new RestUtils();
	SharedPreferences userInfo = null;
	private Convo convo;
	private String userID = "";
	private ArrayList<Message> messages;
	private convoAdapter adapter = null;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conversation);
		userInfo = getSharedPreferences("dev.rug.shyhi",MODE_PRIVATE);
		//get convo id, which will be passed as an intent extra
		Intent intent = getIntent();
	    userID = intent.getStringExtra("idExtra");	
	}

	@Override
	protected void onResume(){
		super.onResume();	
		convo = restUtil.getConvoById(userID);
		// pass context and data to the custom adapter
		messages = convo.getMessages();
	    adapter = new convoAdapter(this, messages);
		ListView lv = (ListView)findViewById(R.id.msgsLv);	
	    //setListAdapter
	    lv.setAdapter(adapter);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.convo, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void putMessage(String msg){
		String otherUser;
		if(convo.getUser1().equals(userID)){
			otherUser = convo.getUser2();
		}
		else{
			otherUser = convo.getUser1();
		}
		Message msgObj = new Message(otherUser,"\""+userID+"\"","\"timestamp\"",msg);
		convo.addMessage(msgObj);
		String convoStr = convo.toStringForPut();
		try {
			String idStr = convo.getId().substring(1,convo.getId().length()-1);
			String putUrl = "http://104.236.22.60:5984/shyhi/"+idStr;
			//new putJSONAsync().execute(putUrl,"{\"_id\": \"739dae773b24dba2a5bdad5bd503a328\",\"_rev\": \"2-cee1e20d1a8b92c9127a895d905c7669\",\"type\": \"convo\",\"user1\": \"user2\",\"user2\":\"user1\",\"messages\": [{\"from\": \"user2\",\"to\": \"user1\",\"message\": \"Potato\",\"timestamp\": \"timestampGoesHere\"},{\"from\": \"user1\",\"to\": \"user2\",\"message\": \"Spud!\",\"timestamp\": \"timestampGoesHere\"},{\"from\": \"user2\",\"to\": \"user1\",\"message\": \"Rawr\",\"timestamp\": \"timestampGoesHere\"}]}");
			new putJSONAsync().execute(putUrl,convoStr);
			Log.i("here we go...methinks",putUrl);
			Log.i("aspdf",convoStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessage(View v){
		Log.i("TESt JSON Stuff",convo.getId()+" "+convo.getRev()+" "+convo.getUser1()+" "+ convo.getMostRecentMessage());
		EditText messageET = (EditText) findViewById(R.id.newmsg);
		if(!messageET.getText().toString().equals("")){
			String newMsg = "\""+messageET.getText().toString()+"\"";
			putMessage(newMsg);
			adapter.notifyDataSetChanged();
		}
	}
	public class putJSONAsync extends AsyncTask<Object, Void, Void> {
	    @Override
	    protected Void doInBackground(Object... params) {
	            String url = (String) params[0];
	            String putStr = (String) params[1];
	            try {
					restUtil.putJSON(url,putStr);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            return null;
	    }
	}
}

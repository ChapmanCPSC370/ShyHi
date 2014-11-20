package dev.rug.shyhi;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;


/*This activity will display the list view of all the users conversations
	it should handle retrieving all of the 
*/
public class ConversationsActivity extends ActionBarActivity {
	
	private ArrayList<Convo> convos;

	private String userID;
	private String convosRestUrl = "http://104.236.22.60:5984/shyhi/_design/conversation/_view/get_all_convos?key=";
	RestUtils restUtil = new RestUtils(); 
	SharedPreferences userInfo = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conversations);
		userInfo = getSharedPreferences("dev.rug.shyhi",MODE_PRIVATE);
		
		ListView lv = (ListView)findViewById(R.id.shysList);
		
		
		
	}
	@Override
	protected void onResume(){
		super.onResume();
		if(userInfo.getBoolean("firstRun", true)){
            userInfo.edit().putBoolean("firstrun", false).commit();
            SharedPreferences.Editor editor = userInfo.edit();
            editor.putString("user_id", "user1"); //need to add a UUID somehow
            editor.commit();
		}
		Log.i("STRING",userInfo.getString("user_id",""));
		getConvo(userInfo.getString("user_id",""));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.conversations, menu);
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
	

	public void getConvo(String id){
		new fetchJSON().execute(id);
		
	}
	private class fetchJSON extends AsyncTask<String, Integer, String> {
        @Override
		protected String doInBackground(String... id) {
        	String convoJSON = restUtil.getJSON(convosRestUrl);
            return convoJSON;
        }
        
        @Override
        protected void onPostExecute(String result) {
        	TextView tv = (TextView) findViewById(R.id.tv1);
        	tv.setText(result);
        }
    }
	
}

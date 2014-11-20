package dev.rug.shyhi;

import java.net.URL;

import android.support.v7.app.ActionBarActivity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ConvoActivity extends ActionBarActivity {

    public static final String PREFS_NAME = "userInfoPrefs";
    public String restUrl = "http://104.236.22.60:5984/shyhi/_design/conversation/_view/get_convo?key=";
	RestUtils restUtil = new RestUtils();
	SharedPreferences userInfo = null;
	private Convo convo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_convo);
		userInfo = getSharedPreferences("dev.rug.shyhi",MODE_PRIVATE);
		//get convo id, which will be passed as an intent extra
		
	}

	@Override
	protected void onResume(){
		super.onResume();
		if(userInfo.getBoolean("firstRun", true)){
            userInfo.edit().putBoolean("firstrun", false).commit();
            SharedPreferences.Editor editor = userInfo.edit();
            editor.putString("user_id", "user1");
            editor.commit();
		}
		Log.i("STRING",userInfo.getString("user_id",""));
		getConvo(userInfo.getString("user_id",""));
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
	
	public void getConvo(String id){
		new fetchJSON().execute(id);
		
	}
	private class fetchJSON extends AsyncTask<String, Integer, String> {
        @Override
		protected String doInBackground(String... id) {
        	String convoJSON = restUtil.getJSON("http://104.236.22.60:5984/shyhi/_design/conversation/_view/get_convo?key="+"%22"+id[0]+"%22");
            return convoJSON;
        }
        
        @Override
        protected void onPostExecute(String result) {
        	TextView tv = (TextView) findViewById(R.id.tv1);
        	tv.setText(result);
        }
    }
}

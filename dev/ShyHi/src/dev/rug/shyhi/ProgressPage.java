package dev.rug.shyhi;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dev.rug.shyhi.ConvoActivity.putJSONAsync;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class ProgressPage extends ActionBarActivity {

	ImageView logo;
	TextView description;
	ProgressBar spinner; 			//Instantiated necessary variables
	RestUtils restUtils = new RestUtils();
	Installation installation = new Installation();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress_page);
		
		logo = (ImageView)findViewById(R.id.imageView1);
		description = (TextView)findViewById(R.id.textView1);
		spinner = (ProgressBar)findViewById(R.id.progressBar1);		//Connected variables to ids
		
		newConvo();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.progress_page, menu);
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
	
	public void newConvo(){
		/*call server, 
		 	server should determine a user to chat with
			server should create and return the document
			app should create the new convo and send intent to start it
		*/
		String otherUserIDwq = restUtils.getRandomUserID(installation.getUUID());
		String otherUserID = otherUserIDwq.substring(1, otherUserIDwq.length()-1);
		String userID = installation.getUUID();
		JSONObject postJsonObj = new JSONObject();
		JSONArray msgArr = new JSONArray();
		try {
		    postJsonObj.put("type", "convo");
		    postJsonObj.put("user1", userID);
		    postJsonObj.put("user2", otherUserID);
		    postJsonObj.put("messages", msgArr);
		} catch (JSONException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		String postStr = postJsonObj.toString();
		String newConvoIdStr = "";
		try {
			String tempStr = new convoAsync().execute(postStr).get();
			JSONObject jo = new JSONObject(tempStr);
			newConvoIdStr = jo.getString("id");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Convo convo = new Convo(newConvoIdStr,userID,otherUserID);
		Intent intent = new Intent(this,ConvoActivity.class);
		intent.putExtra("idExtra",newConvoIdStr);
		startActivity(intent);
	}
	public class convoAsync extends AsyncTask<Object, Void, String> {
		@Override
		protected String doInBackground(Object... params) {
			String retJson ="";
			String postStr = (String) params[0];
			try {
				retJson = restUtils.postJSON(RestUtils.dev_server_str, postStr);
				return(retJson);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;	
		}
		@Override
	    protected void onPostExecute(String result) {    	
	        }
	}
	
}

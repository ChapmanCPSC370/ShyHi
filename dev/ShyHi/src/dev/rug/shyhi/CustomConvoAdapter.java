package dev.rug.shyhi;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomConvoAdapter extends BaseAdapter implements OnClickListener{
	
	
	private Activity activity;
	private ArrayList<Convo> convos;
	private Context context;
	private static LayoutInflater inflater = null; 
	public Resources res;
	Convo tempValues = null;
	int i = 0;									//Declared necessary variables
	//Custom Adapter Constructor
	public CustomConvoAdapter(Activity a, ArrayList<Convo> d)/*, Resources resLocal)*/
	{
		activity = a;
		convos = d;
		//res = resLocal;							//Reassign past values
		//Layout inflater to call xml
		inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		
	}
	
	//Get number of items to display
	public int getCount() {
          
        if(convos.size()<=0)
            return 1;
        return convos.size();
    }
	
	public Object getItem(int position) {
        return position;
    }
  
    public long getItemId(int position) {
        return position;
    }
      
    //Create a holder Class to contain inflated xml file elements 
    public static class ViewHolder{
         
    	public TextView anonNumber;
    	public ImageView onlineStatus;
    	public TextView convoText;
    	public TextView textDate;
    	 
    }
    
    
    //Create each listRowItem 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View vi = convertView;
		ViewHolder holder;
		
		if(convertView == null)
		{
			//Inflate listview with each row
			vi = inflater.inflate(R.layout.convo_custom_layout, null);
			
			//View Holder to contain elements
			holder = new ViewHolder();
			holder.anonNumber = (TextView)vi.findViewById(R.id.textView1);
			holder.onlineStatus = (ImageView)vi.findViewById(R.id.imageView1);
			holder.convoText = (TextView)vi.findViewById(R.id.textView3);
			holder.textDate = (TextView)vi.findViewById(R.id.textView2);
			
			//Set holder with inflater
            vi.setTag( holder );
			
		}
		else 
            holder=(ViewHolder)vi.getTag();
         
        if(convos.size()<=0)
        {
            holder.anonNumber.setText("No Data");				//In case no conversations
             
        }
        else
        {
            //Get each model object 
            tempValues=null;
            tempValues = (Convo)convos.get( position );
    		Installation installation = new Installation();
    		String thisUser = installation.getUUID();
            //Sets model values
    		holder.anonNumber.setText(randomNameGen.randomName());
            //holder.anonNumber.setText(tempValues.getOtherUser(thisUser).substring(1, tempValues.getOtherUser(thisUser).length()-1));	//Is this the anon#? 
            holder.convoText.setText(tempValues.getMostRecentMessage());
            holder.textDate.setText(tempValues.getMostRecentTime());
        }
        return vi;
    }
     
    @Override
    public void onClick(View v) {
            Log.v("CustomAdapter", "=====Row button clicked=====");
    }
     
   
}

        

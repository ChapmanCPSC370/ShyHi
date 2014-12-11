package dev.rug.shyhi;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class convoAdapter extends ArrayAdapter<Message>{

	private final Context context;
	Installation installation = new Installation();
	private String userID = installation.getUUID();
    private ArrayList<Message> itemsArrayList;
    public convoAdapter(Context context, ArrayList<Message> itemsArrayList) {
        super(context, R.layout.list_item, itemsArrayList);
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }	    
    
	//get custom list view item, inflate, and set proper data
	public View getView(int position, View convertView, ViewGroup parent) {
		//Create inflater 
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//init row view
		String messageFrom = itemsArrayList.get(position).getFromID().substring(1, itemsArrayList.get(position).getFromID().length()-1);
		if(messageFrom.equals(userID)){
			
			View rowView = inflater.inflate(R.layout.left_message, parent, false);
			//get and set layout elements
			TextView msgText = (TextView) rowView.findViewById(R.id.recentMsg);
			
			//set the textviews by accessing convo object
			msgText.setText((itemsArrayList.get(position).getMessageNoQuotes()));
				return rowView;
		}
		else{
			
			View rowView = inflater.inflate(R.layout.right_message, parent, false);
			//get and set layout elements
			TextView msgText = (TextView) rowView.findViewById(R.id.recentMsg);
			
			//set the textviews by accessing convo object
			msgText.setText((itemsArrayList.get(position).getMessageNoQuotes()));
				return rowView;
		}
			
		
	}
}

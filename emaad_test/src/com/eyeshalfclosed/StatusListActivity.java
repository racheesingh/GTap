package com.eyeshalfclosed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class StatusListActivity extends Activity {
    
    Integer[] imageIDs = {
            R.drawable.candidate1,
            R.drawable.candidate2,
            R.drawable.candidate3,
            R.drawable.candidate4              
    };
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(this));
        
        gridView.setOnItemClickListener(new OnItemClickListener() 
        {
            public void onItemClick(AdapterView parent, 
            View v, int position, long id) 
            {                
                Toast.makeText(getBaseContext(), 
                        "pic" + (position + 1) + " selected", 
                        Toast.LENGTH_SHORT).show();
                Intent showQuickInfo = new Intent("com.eyeshalfclosed.QuickInfo");
                Bundle params = new Bundle();
                params.putString("candidateName", "Candidate" + (position + 1));
                showQuickInfo.putExtras(params);
                showQuickInfo.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(showQuickInfo);
            }
        });        

    }
    public class ImageAdapter extends BaseAdapter 
    {
        private Context context;
 
        public ImageAdapter(Context c) 
        {
            context = c;
        }
 
        //---returns the number of images---
        public int getCount() {
            return imageIDs.length;
        }
 
        //---returns the ID of an item--- 
        public Object getItem(int position) {
            return position;
        }
 
        public long getItemId(int position) {
            return position;
        }
 
        //---returns an ImageView view---
        public View getView(int position, View convertView, ViewGroup parent) 
        {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(80, 80));
                //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                //imageView.setPadding(5, 5, 5, 5);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setImageResource(imageIDs[position]);
            return imageView;
        }
    }
}
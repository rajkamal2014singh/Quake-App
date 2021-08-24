package com.example.earthquake;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

        public static final String LOG_TAG = MainActivity.class.getName();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            // Create a fake list of earthquake locations.
           /* ArrayList<Data> earthquakes = new ArrayList<Data>();
            earthquakes.add(new Data("4.2","San Francisco","Feb 02,2016 "));
            earthquakes.add(new Data("4.2","London","Feb 02,2016 "));
            earthquakes.add(new Data("4.2","Tokyo","Feb 02,2016 "));
            earthquakes.add(new Data("4.2","Mexico City","Feb 02,2016 "));
            earthquakes.add(new Data("4.2","Moscow","Feb 02,2016 "));
            earthquakes.add(new Data("4.2","Rio de Janeiro","Feb 02,2016 "));
            earthquakes.add(new Data("4.2","Paris","Feb 02,2016 "));
            */
            ArrayList<Data> earthquakes = QuerryUtils.extractEarthquakes();


            //Using custom created WordAdapter that can take two textView as input
            DataAdapter quakeAdapter = new DataAdapter(this, earthquakes);
            ListView quakeListView = (ListView) findViewById(R.id.list);
            quakeListView.setAdapter(quakeAdapter);

            quakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // Get the {@link Word} object at the given position the user clicked on
                    Data data = earthquakes.get(position);
//                    Intent url = new Intent(Intent.ACTION_VIEW);
//                    url.setData(Uri.parse(data.getURL()));
                    Uri eartquakeUri = Uri.parse(data.getURL());
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW,eartquakeUri);
                    if(websiteIntent.resolveActivity(getPackageManager())!=null)
                        startActivity(websiteIntent);

//
//                    // Convert the String URL into a URI object (to pass into the Intent constructor)
//                    Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());
//
//                    // Create a new intent to view the earthquake URI
//                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
//
//                    // Send the intent to launch a new activity
//                    startActivity(websiteIntent);

                }
            });

        }
    }
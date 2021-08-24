package com.example.earthquake;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.graphics.drawable.GradientDrawable;

public class DataAdapter extends ArrayAdapter<Data> {

    private static final String LOCATION_SEPARATOR = " of ";

    public DataAdapter(Activity Context, ArrayList<Data> datas) {
        super(Context, 0, datas);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double Magnitude) {
        int magnitudeColorResuorceId;
        int magnitudeFloor = (int) Math.floor(Magnitude);

        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResuorceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResuorceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResuorceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResuorceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResuorceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResuorceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResuorceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResuorceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResuorceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResuorceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResuorceId);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Data currentData = getItem(position);


        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        // Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(currentData.getMagnitude());
        // Display the magnitude of the current earthquake in that TextView
        magnitudeTextView.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentData.getMagnitude());
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);



       /* // Find the TextView in the list_item.xml layout with the ID version_number
        TextView placeTextView = (TextView) listItemView.findViewById(R.id.place_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        placeTextView.setText(currentData.getPlace());
*/
        //Changed Code
        String originalLocation = currentData.getPlace();
        String primaryLocation;
        String locationOffset;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }


//        String getLocation = currentData.getPlace();
//        String[] getArrayString = getLocation.split("(?<=of)",2);
//
//        String PrimaryLocation =getArrayString[1];
//        String Offset = getArrayString[0] + " of";

//        Offset = getArrayString[0];
//        PrimaryLocation = getArrayString[1];
//
//        if(PrimaryLocation == "")
//        {
//            Offset = "Nearby";
//            PrimaryLocation = getArrayString[0];
//        }
        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView offsetTextView = (TextView) listItemView.findViewById(R.id.place_offset_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        offsetTextView.setText(locationOffset);
        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView placeTextView = (TextView) listItemView.findViewById(R.id.place_primary_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        placeTextView.setText(primaryLocation);
      /*  // Find the TextView in the list_item.xml layout with the ID version_number
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        dateTextView.setText(currentData.getDate());
*/

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentData.getTimeInMilliSeconds());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date_text_view);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time_text_view);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);

//        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
//        ImageView iconView = (ImageView) listItemView.findViewById(R.id.image_view);
//        // Get the image resource ID from the current AndroidFlavor object and
//        // set the image to iconView
//        iconView.setImageResource(currentWord.getImageResourceId());
//
//        //Set the theme for the list item
//        View itemContainer = listItemView.findViewById(R.id.container_text);
//        // Find the color that the resource ID maps to
//        int color = ContextCompat.getColor(getContext(),mColorResourceId);
//        //set the background color of the container view
//        itemContainer.setBackgroundColor(color);


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}


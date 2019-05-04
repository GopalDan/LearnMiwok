package com.example.gopal.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Gopal on 10/10/2018.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    private int mColorResourceId;
    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId ) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        TextView defaultWord = (TextView) listItemView.findViewById(R.id.defaut_Translation);
        defaultWord.setText(currentWord.getDefaultTranslation());

        TextView miwokWord = (TextView) listItemView.findViewById(R.id.miwok_translation);
        miwokWord.setText(currentWord.getMiwokTranslation());

        ImageView image = listItemView.findViewById(R.id.image);
        if(currentWord.getImageImageResourceId()!=-1){
        image.setImageResource(currentWord.getImageImageResourceId());}
        else
        {
            image.setVisibility(View.GONE);
        }

        /*
        We have to convert color resource id to a color value so that it can refer a color.
        Color resource id doesn't refer any color.
         */
        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(),mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;

    }

}

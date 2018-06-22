package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
    private int mColorResourceID;

    public WordAdapter(Activity context, ArrayList<Word> words) {
        super(context, 0, words);
     }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        final Word currentWord = getItem(position);

        TextView miwokTranslationTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTranslationTextView.setText(currentWord.getMiwokTranslation());
        TextView defaultTranslationTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTranslationTextView.setText(currentWord.getmDefaultTranslation());
        ImageView imageImageView = (ImageView) listItemView.findViewById(R.id.iamge_image_view);
        int imageID = currentWord.getImageResourceID();
        if (imageID != 0) imageImageView.setImageResource(imageID);
        else imageImageView.setVisibility(View.GONE);
        return listItemView;
    }
}

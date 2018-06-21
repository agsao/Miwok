/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        OnClickOpenActivity(R.id.numbers, NumbersActivity.class);
        OnClickOpenActivity(R.id.family, FamilyActivity.class);
        OnClickOpenActivity(R.id.colors, ColorsActivity.class);
        OnClickOpenActivity(R.id.phrases, PhrasesActivity.class);
    }

    private void OnClickToastMessage(int textViewID, final String message) {
        TextView textView = (TextView) findViewById(textViewID);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void OnClickOpenActivity(int textViewID, final Class activityClass) {
        TextView textView = (TextView) findViewById(textViewID);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, activityClass);
                startActivity(i);
            }
        });

    }

    public void openNumbersList(View view) {
        Intent i = new Intent(this, NumbersActivity.class);
        startActivity(i);
    }

    public void openFamilyList(View view) {
        Intent i = new Intent(this, FamilyActivity.class);
        startActivity(i);
    }

    public void openPhrasesList(View view) {
        Intent i = new Intent(this, PhrasesActivity.class);
        startActivity(i);
    }

    public void openColorsList(View view) {
        Intent i = new Intent(this, ColorsActivity.class);
        startActivity(i);
    }


    //Old Code
    //NumbersClickListener NumbersClick=new NumbersClickListener();
    //((TextView) findViewById(R.id.numbers)).setOnClickListener(NumbersClick);
//        ((TextView) findViewById(R.id.numbers)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"Numbers List",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//

//        ((TextView) findViewById(R.id.family)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"Family Members",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        ((TextView) findViewById(R.id.colors)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"Colors List",Toast.LENGTH_SHORT).show();
//            }
//        });
//        ((TextView) findViewById(R.id.phrases)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"Phrases List",Toast.LENGTH_SHORT).show();
//            }
//        });

    //OnClickToastMessage(R.id.numbers,madLib);
//        OnClickToastMessage(R.id.family,"Family Members");
//        OnClickToastMessage(R.id.colors,"Colors List");
//        OnClickToastMessage(R.id.phrases,"Phrases List");

}

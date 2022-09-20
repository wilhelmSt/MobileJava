package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.view.View;
import android.widget.ToggleButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import java.util.ArrayList;
import android.widget.Spinner;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleButton1;
    private Button buttonList, buttonToAct2, buttonHold;
    private EditText textList;
    private ListView listview;
    private ArrayList<String> arraylist;
    private ArrayAdapter<String> adapter;
    private MediaPlayer mySound;

    // autocomplete
    private static final String[] fruits = new String[]{"maçã", "banana", "cereja",
            "manga", "uva", "kiwi", "morango", "pera", "amora", "goiaba"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ListAdd
        textList = (EditText) findViewById(R.id.editTextList);
        buttonList = (Button) findViewById(R.id.buttonList);
        listview = (ListView) findViewById(R.id.listView);
        arraylist = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, arraylist);
        listview.setAdapter(adapter);

        // AutoComplete
        AutoCompleteTextView edText = findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> adapterAutoComplete = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, fruits);
        edText.setThreshold(1);
        edText.setAdapter(adapterAutoComplete);

        // Spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.spinnerString));
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);

        // Activity 2
        buttonToAct2 = (Button) findViewById(R.id.buttonToAct2);
        buttonToAct2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        // ButtonHold + Sound
        buttonHold = findViewById(R.id.buttonHold);
        mySound = MediaPlayer.create(this, R.raw.sound);

        buttonHold.setOnTouchListener(new View.OnTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(),
                    new GestureDetector.SimpleOnGestureListener(){
                @Override
                public void onLongPress(MotionEvent e) {
                    mySound.start();
                    Toast.makeText(getApplicationContext(), "Long Press", Toast.LENGTH_SHORT).show();
                    super.onLongPress(e);
                }
            });

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return false;
            }
        });


        onClickButtonList();
    }

    public void openActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    public void onClickButtonList() {
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = textList.getText().toString();
                arraylist.add(result);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    LinearLayout textEdit ;
    EditText textBox;

    String selectedCity = "Edmonton";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityList = findViewById(R.id.city_list);

        String[] cities = {"Edmonton", "Calgary", "Ottawa", "Winnipeg", "Victoria", "Montreal", "Toronto", "St. John's", "Charlottetown", "Halifax"};

        dataList = new ArrayList<>();

        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, R.id.content_view, dataList);

        cityList.setAdapter(cityAdapter);

    }

    public void showTextEdit(View v){
        textEdit = findViewById(R.id.textEditView);
        textEdit.setVisibility(View.VISIBLE);

        textBox = findViewById((R.id.textbox));
        textBox.requestFocus();

        showKeyboard();
    }

    private void showKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
        imm.showSoftInput(textBox, InputMethodManager.SHOW_IMPLICIT);
    }

    protected void hideTextEdit(){
        textEdit =  findViewById(R.id.textEditView);
        textEdit.setVisibility(View.GONE);
    }

    public void confirmAdd(View v){
        textBox = findViewById(R.id.textbox);

        String city = textBox.getText().toString();
        dataList.add(city);


        hideKeyboard();

        hideTextEdit();

    }

    private void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
        View view = this.getCurrentFocus();
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}

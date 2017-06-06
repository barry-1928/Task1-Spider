package com.example.dell.task1_spider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    EditText editText1;
    EditText editText2;
    String s;
    int k;
    ListView listView;
    ArrayAdapter<String> arrayAdapter = null;
    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null) {
            arrayList = savedInstanceState.getStringArrayList("MyArrayList");
            Log.d("Test",arrayList.get(0));
        }
        setContentView(R.layout.activity_main);
        editText1 = (EditText) findViewById(R.id.edittext1);
        editText2 = (EditText) findViewById(R.id.edittext2);
        listView = (ListView) findViewById(R.id.listview);
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.single_row,R.id.textview,arrayList);
        listView.setOnItemClickListener(this);
        listView.setAdapter(arrayAdapter);
    }

    public void add_to_list(View view) {
        s = editText1.getText().toString();
        if(!(s.equals(""))) {
            arrayList.add(s);
            arrayAdapter.notifyDataSetChanged();
            editText1.setText("");
        }
    }

    public void remove_from_list(View view) {
        s = editText2.getText().toString();
        if(!(s.equals(""))) {
            k = Integer.parseInt(s);
            if (k > arrayList.size()) {
                Toast.makeText(this, "The item could not be found. Please try again.", Toast.LENGTH_SHORT).show();
            } else {
                arrayList.remove(k);
                arrayAdapter.notifyDataSetChanged();
                editText2.setText("");
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this,ActivityB.class);
        i.putExtra("Access",arrayList.get(position));
        startActivity(i);
    }

    @Override
    protected void onDestroy() {
        Log.d("Test", "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putStringArrayList("MyArrayList",arrayList);
    }
}

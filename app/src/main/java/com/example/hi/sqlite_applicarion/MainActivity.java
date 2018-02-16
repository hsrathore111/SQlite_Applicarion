package com.example.hi.sqlite_applicarion;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et1, et2, et3, et4;

    SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);

        database = openOrCreateDatabase("mydatabase", Context.MODE_PRIVATE, null);
        database.execSQL("create table if not exists employee (id number,name varchar(50),design varchar(50),position varchar(50))");

    }


    public void Insert(View v) {

        ContentValues values =new ContentValues();
        values.put("id",Integer.parseInt(et1.getText().toString()));
        values.put("name",et2.getText().toString());
        values.put("design",et3.getText().toString());
        values.put("position",et4.getText().toString());
        long count=database.insert("employee",null,values);
        et1.setText(" ");
        et2.setText(" ");
        et3.setText(" ");

        et4.setText(" ");

        if(count>0)
        {
            Toast.makeText(getApplicationContext(),"Data is inserted....",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Data is not inserted....",Toast.LENGTH_LONG).show();
        }
    }


    public void Read(View v) {
        Cursor cursor =database.query("employee",null,null,null,null,null,null);
        while(cursor.moveToNext())
        {
            Toast.makeText(getApplicationContext(),cursor.getString(0)+"\n"+cursor.getString(1)+"\n"+cursor.getString(2)+"\n"+cursor.getString(3),Toast.LENGTH_LONG).show();
        }
    }

    public void Update(View v) {

        ContentValues values =new ContentValues();
        values.put("id",Integer.parseInt(et1.getText().toString()));
        values.put("name",et2.getText().toString());
        values.put("design",et3.getText().toString());
        values.put("position",et4.getText().toString());
       long count= database.update("employee",values,"id=?",new String[]{et1.getText().toString() });
        if(count>0)
        {
            Toast.makeText(getApplicationContext(),"Data is updated....",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Data is not updated....",Toast.LENGTH_LONG).show();
        }
    }




    public void Delete(View v) {


      long  count=  database.delete("employee","id=?",new String[]{et1.getText().toString()});

  if(count>0)
    {
        Toast.makeText(getApplicationContext(),"Data is deleted....",Toast.LENGTH_LONG).show();
    }
        else
    {
        Toast.makeText(getApplicationContext(),"Data is not deleted....",Toast.LENGTH_LONG).show();
    }
}

}



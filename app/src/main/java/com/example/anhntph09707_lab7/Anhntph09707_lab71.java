package com.example.anhntph09707_lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Anhntph09707_lab71 extends AppCompatActivity {
    Button btnGhi,btnDoc;
    EditText txt;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anhntph09707_lab71);
        txt = findViewById(R.id.txt);
        tv = findViewById(R.id.tv);
        btnDoc = findViewById(R.id.demo71BtnDoc);
        btnGhi = findViewById(R.id.demo71BtnGhi);
        btnGhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ghiDuLieu(txt.getText().toString());
                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                }catch (Exception exception){
                    Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = docDulieu();
                tv.setText(s);
                Log.e("s",""+s);

            }
        });


    }
    public void ghiDuLieu(String dulieu)
    {
        //B1- lay duong dan
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                +"/data1.txt";
        //b2- Thuc hien tao luong ghi
        try {
            OutputStreamWriter o
                    = new OutputStreamWriter(new FileOutputStream(path));
            //b3- ghi
            o.write(dulieu);
            //b4- dong luong
            o.close();
        }
        catch (FileNotFoundException e) {
           // Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (IOException e) {
         //   Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    public String docDulieu()
    {
        String dulieu="";
        //B1- lay duong dan
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                +"/data1.txt";
        try {
            Scanner s = new Scanner(new File(path));
            while (s.hasNext())
            {
                dulieu = s.nextLine()+"\n";
            }
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dulieu;
    }
}
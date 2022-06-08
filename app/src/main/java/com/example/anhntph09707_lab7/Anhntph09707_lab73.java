package com.example.anhntph09707_lab7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Anhntph09707_lab73 extends AppCompatActivity {
    Button btnLogin;
    EditText txtU,txtP;
    CheckBox chk;
    List<Object> ls = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anhntph09707_lab73);
        btnLogin = findViewById(R.id.demo73BtnLogin);
        txtU = findViewById(R.id.demo73TxtName);
        txtP = findViewById(R.id.demo73TxtPass);
        chk = findViewById(R.id.demo73checkBox);

        ls = restorePass();
        if(ls.size()>0)
        {
            txtU.setText(ls.get(0).toString());
            txtP.setText(ls.get(1).toString());
            chk.setChecked((boolean)ls.get(2));
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String u = txtU.getText().toString();
                String p = txtP.getText().toString();
                if(u.isEmpty() || p.isEmpty())//neu nguoi dung khong nhap
                {
                    Toast.makeText(getApplicationContext(),"U,P khong de trong",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if(u.equalsIgnoreCase("admin")&&
                        p.equalsIgnoreCase("admin"))
                {
                    savePass(u,p,chk.isChecked());
                    Toast.makeText(getApplicationContext(),"Dang nhap thanh cong",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void savePass(String u,String p,boolean status)
    {
        //b1- tạo file cần lưu
        SharedPreferences sharedPreferences
                = getSharedPreferences("HUNG_FILE",MODE_PRIVATE);

        //b2- bật chế độ editor
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(!status)//neu da co pass
        {
            editor.clear();//xoa pass cu
        }
        else
        {
            //luu tru du lieu moi
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",status);
        }
        //đẩy dữ liệu lên file
        editor.commit();
    }
    public List<Object> restorePass()
    {
        List<Object> list = new ArrayList<>();
        //B1 -  mở file
        SharedPreferences sharedPreferences
                =getSharedPreferences("HUNG_FILE",MODE_PRIVATE);
        //b2- kiểm tra yêu cầu đọc hay không
        boolean check = sharedPreferences.getBoolean("REMEMBER",false);
        if(check)//nếu cho phép đọc dữ liệu từ bộ nhớ shared
        {
            String u = sharedPreferences.getString("USERNAME","");
            String p = sharedPreferences.getString("PASSWORD","");
            list.add(u);
            list.add(p);
            list.add(check);
        }
        return list;
    }
}
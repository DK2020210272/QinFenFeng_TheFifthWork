package com.example.qinfenfeng_the_fifthwork;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText account_number;
    private EditText password;

    private ArrayList array_account_number = new ArrayList();
    private ArrayList array_password = new ArrayList();

    private int flag = -1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //适配状态栏颜色
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); getWindow().setStatusBarColor(ContextCompat.getColor(this,android.R.color.holo_blue_dark));

        //隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        //获取editText
        account_number = (EditText) findViewById(R.id.account_number);
        password = (EditText) findViewById(R.id.password);
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());


        //获取Button
        Button sign_in = (Button) findViewById(R.id.Sign_in);
        sign_in.setOnClickListener(new View.OnClickListener() {

            Intent intent_Rv = new Intent(MainActivity.this,AccountActivity.class);

            @Override
            public void onClick(View v) {
                for(int i=0;i<array_account_number.size();i++){
                    if(account_number.getText().toString().equals(array_account_number.get(i))){
                        if(password.getText().toString().equals(array_password.get(i))){
                            flag=1;
                        }else{
                            flag=0;
                        }
                    }
                }
                if(flag==1){
                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    startActivity(intent_Rv);
                }else if(flag==0){
                    Toast.makeText(MainActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"该用户不存在，请先注册",Toast.LENGTH_SHORT).show();
                }
                flag=-1;
            }
        });

        //跳转到注册界面
        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent_to_register =  new Intent(MainActivity.this,RegisterActivity.class);
                intent_to_register.putExtra("data_account_number_in",array_account_number);
                intent_to_register.putExtra("data_password_in",array_password);
                startActivityForResult(intent_to_register,1);
            }
        });
    }

    //获取销毁活动传来的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    array_account_number = data.getStringArrayListExtra("data_account_number");
                    array_password = data.getStringArrayListExtra("data_password");
                }
        }
    }
}
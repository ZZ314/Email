package com.example.zz.email3;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button login;
    private Button exist;
    private EditText user;
    private  EditText password;
    private long exitTime=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setTitle("请登录");

        if(Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        user=(EditText)findViewById(R.id.user);
        password=(EditText)findViewById(R.id.password);
        exist=(Button)findViewById(R.id.exist);
        login=(Button)findViewById(R.id.login);

        exist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    String yonghu=user.getText().toString();
                    String pass=password.getText().toString();

                    String s= Email.login(yonghu,pass);
                    if(s.equals("235"))
                    {
                        Toast.makeText(MainActivity.this,"登录成功！！！！！",Toast.LENGTH_LONG).show();
                        Intent i=new Intent(MainActivity.this,SendActivity.class);
                        startActivity(i);
                    }
                    else
                    Toast.makeText(MainActivity.this,"登录失败！！！！！",Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            }
        });
   }
}

//创建邮件的连接属性
//        Properties emailProperties=new Properties();
//        emailProperties.put("mail.smtp.host","amtp.163.com");
//        emailProperties.put("mail.smtp.auth",true);
//创建认证实例
//PopupAuthen emailAuthen=new PopupAuthen();
//创建会话
// final SpellCheckerService.Session emailSession=Sessoin.get

// Android 4.0 之后不能在主线程中请求HTTP请求
//new Thread(new Runnable(){
//@Override
//public void run() {
//        cachedImage = asyncImageLoader.loadDrawable(imageUrl, position);
//        imageView.setImageDrawable(cachedImage);
//        }
//        }).start();


package com.example.zz.email3;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class SendActivity extends AppCompatActivity {
    private EditText fa;
    private EditText shou;
    private EditText subject;
    private EditText text;

    private Button send;
    private Button del;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        this.setTitle("写信");

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        fa=(EditText)findViewById(R.id.fa);
        shou=(EditText)findViewById(R.id.shou);
        subject=(EditText)findViewById(R.id.subject);
        text=(EditText)findViewById(R.id.text);
        send=(Button) findViewById(R.id.send);
        del=(Button)findViewById(R.id.del);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    String from=fa.getText().toString();
                    String to=shou.getText().toString();
                    String sub=subject.getText().toString();
                    String content=text.getText().toString();

                    Email.sendMail(from,to,sub,content);
                        Toast.makeText(SendActivity.this,"发送成功！！！！",Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}

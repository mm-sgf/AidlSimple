package com.cfox.aidlservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cfox.aidllibrary.bean.ProcessUserBean;

public class MainActivity extends AppCompatActivity {

    private Button mBtnOpenIn,mBtnOpenOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnOpenIn = (Button) findViewById(R.id.btn_open_in);
        mBtnOpenIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,InActivity.class);
                startActivity(intent);

            }
        });

        mBtnOpenOut = (Button) findViewById(R.id.btn_open_out);
        mBtnOpenOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProcessActivity.class);
                startActivity(intent);
            }
        });
    }
}

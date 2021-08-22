package com.cfox.aidlcline;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cfox.aidllibrary.IAIDLService;
import com.cfox.aidllibrary.bean.ProcessUserBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ProcessActivity";
    private Button mBtnStart,mBtnStop,mBtnGetData,mBtnSetData;

    private IAIDLService iaidlService;

    private ServiceConnection conn;

    private class MyConn implements ServiceConnection{
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iaidlService = IAIDLService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iaidlService = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnStart = (Button) findViewById(R.id.btn_start_service);
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("com.cfox.myprocess");
                intent.setPackage("com.cfox.aidlservice");
                conn = new MyConn();
                bindService(intent,conn, Context.BIND_AUTO_CREATE);
            }
        });

        mBtnStop = (Button) findViewById(R.id.btn_stop_service);
        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);
            }
        });

        mBtnGetData = (Button) findViewById(R.id.btn_get_data);
        mBtnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    showUser(iaidlService.getUser());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });

        mBtnSetData = (Button) findViewById(R.id.btn_set_data);
        mBtnSetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iaidlService.setProcessUser(createUser());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private ProcessUserBean createUser(){
        ProcessUserBean user = new ProcessUserBean();
        user.setName("张三");
        user.setAge(58);
        List<String> list = new ArrayList<String>();
        list.add("good good ");
        user.setList(list);

        Map<String,String> map = new HashMap<String,String>();
        map.put("addr","beijing");
        user.setMap(map);

        return user;
    }


    private void showUser(ProcessUserBean user){

        String name = user.getName();
        Log.e(TAG,"ProcessActivity UserInfo Name:" + name);
        int age = user.getAge();
        Log.e(TAG,"ProcessActivity UserInfo age:" + age);
        List<String> listString =  user.getList();
        for(int i = 0 ; i < listString.size(); i ++){
            Log.e(TAG,"ProcessActivity UserInfo list:" + listString.get(i));

        }

        Map<String,String> map = user.getMap();
        Set<String> keys = map.keySet();
        for(String key : keys){
            String value = map.get(key);
            Log.e(TAG,"ProcessActivity UserInfo Map->key:" + key + ";value" + map.get(key));
        }
    }
}

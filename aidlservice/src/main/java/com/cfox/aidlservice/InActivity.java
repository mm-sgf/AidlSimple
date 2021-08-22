package com.cfox.aidlservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cfox.aidllibrary.bean.Dog;
import com.cfox.aidllibrary.bean.UserBean;
import com.cfox.aidlservice.intf.ServiceControl;
import com.cfox.aidlservice.service.InService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <br/>************************************************
 * <br/>PROJECT_NAME : AidlService
 * <br/>PACKAGE_NAME : com.cfox.aidlservice
 * <br/>AUTHOR : Machao
 * <br/>DATA : 2016/6/24 0024
 * <br/>TIME : 10:42
 * <br/>MSG :
 * <br/>************************************************
 */
public class InActivity extends Activity {

    private static final String TAG = "InActivity";

    private Button mBtnStart,mBtnStop,mBtnGetData,mBtnSetData;

    private ServiceConnection conn;
    private ServiceControl serviceControl;

    private Context context;

    public class MyConn implements ServiceConnection{

        public MyConn(){}


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            serviceControl = (ServiceControl) service;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceControl = null;
        }
    }

    private UserBean createUser(){
        UserBean user = new UserBean();
        user.setName("张三");
        user.setAge(58);
        List<String> list = new ArrayList<String>();
        list.add("good good ");
        user.setList(list);

        Map<String,String> map = new HashMap<String,String>();
        map.put("addr","beijing");
        user.setMap(map);

        Dog dog = new Dog();
        dog.setName("大黄");
        dog.setColor("RED");
        user.setDog(dog);

        List<Dog> listDog = new ArrayList<Dog>();
        listDog.add(dog);
        user.setLsitDog(listDog);
        return user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in);

        context = this;
        mBtnStart = (Button) findViewById(R.id.btn_start_service);
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(InActivity.this, InService.class);
                conn = new MyConn();
                context.bindService(intent,conn, Context.BIND_AUTO_CREATE);
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
                showUser(serviceControl.getUser());
            }
        });

        mBtnSetData = (Button) findViewById(R.id.btn_set_data);
        mBtnSetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serviceControl.setUser(createUser());
            }
        });
    }



    private void showUser(UserBean user){

        String name = user.getName();
        Log.e(TAG,"InActivity UserInfo Name:" + name);
        int age = user.getAge();
        Log.e(TAG,"InActivity UserInfo age:" + age);
        List<String> listString =  user.getList();
        for(int i = 0 ; i < listString.size(); i ++){
            Log.e(TAG,"InActivity UserInfo list:" + listString.get(i));

        }

        Map<String,String> map = user.getMap();
        Set<String> keys = map.keySet();
        for(String key : keys){
            String value = map.get(key);
            Log.e(TAG,"InActivity UserInfo Map->key:" + key + ";value" + map.get(key));
        }

        Dog dog = user.getDog();
        String dogName = dog.getName();
        Log.e(TAG,"InActivity UserInfo DogName:" + dogName);
        String dogColor = dog.getColor();
        Log.e(TAG,"InActivity UserInfo dogColor:" + dogColor);

        List<Dog> dogs = user.getLsitDog();
        for(int i = 0 ; i < dogs.size(); i ++){
            Dog dogl = dogs.get(i);
            String doglName = dogl.getName();
            Log.e(TAG,"InActivity UserInfo doglName:" + doglName);
            String doglColor = dogl.getColor();
            Log.e(TAG,"InActivity UserInfo doglColor:" + doglColor);
        }
    }

}

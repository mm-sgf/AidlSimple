package com.cfox.aidlservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.cfox.aidllibrary.bean.Dog;
import com.cfox.aidllibrary.bean.UserBean;
import com.cfox.aidlservice.intf.ServiceControl;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class InService extends Service{
    private final String TAG = "InService";

    private IBinder iBinder = new MyBinder();

    private String mName;
    private UserBean userBean;

    private class MyBinder extends Binder implements ServiceControl{
        @Override
        public void setServiceName(String name) {
            mName = name;
        }

        @Override
        public String getServiceName() {
            return mName;
        }

        @Override
        public void setUser(UserBean user) {
            Log.e(TAG,"InService setUser run ......");
            userBean = user;
            showUser(userBean);
            Log.e(TAG,"====================================================");
        }

        @Override
        public UserBean getUser() {
            Log.e(TAG,"InService getUser run ......");
            showUser(userBean);
            Log.e(TAG,"====================================================");
            return userBean;
        }
    }

    private void showUser(UserBean user){

        String name = user.getName();
        Log.e(TAG,"InService UserInfo Name:" + name);
        int age = user.getAge();
        Log.e(TAG,"InService UserInfo age:" + age);
        List<String> listString =  user.getList();
        for(int i = 0 ; i < listString.size(); i ++){
            Log.e(TAG,"InService UserInfo list:" + listString.get(i));

        }

        Map<String,String> map = user.getMap();
        Set<String> keys = map.keySet();
        for(String key : keys){
            String value = map.get(key);
            Log.e(TAG,"InService UserInfo Map->key:" + key + ";value" + map.get(key));
        }

        Dog dog = user.getDog();
        String dogName = dog.getName();
        Log.e(TAG,"InService UserInfo DogName:" + dogName);
        String dogColor = dog.getColor();
        Log.e(TAG,"InService UserInfo dogColor:" + dogColor);

        List<Dog> dogs = user.getLsitDog();
        for(int i = 0 ; i < dogs.size(); i ++){
            Dog dogl = dogs.get(i);
            String doglName = dogl.getName();
            Log.e(TAG,"InService UserInfo doglName:" + doglName);
            String doglColor = dogl.getColor();
            Log.e(TAG,"InService UserInfo doglColor:" + doglColor);
        }
    }

    @Override
    public void onCreate() {
        Log.e(TAG,"InService onCreate run ......");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"InService onBind run ......");
        return iBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"InService onStartCommand run ......");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.e(TAG,"InService onRebind run ......");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG,"InService onUnbind run ......");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"InService onDestroy run ......");
        super.onDestroy();
    }
}

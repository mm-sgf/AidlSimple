package com.cfox.aidlservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.cfox.aidllibrary.IAIDLService.Stub;
import com.cfox.aidllibrary.IServerListener;
import com.cfox.aidllibrary.bean.Dog;
import com.cfox.aidllibrary.bean.ProcessUserBean;
import com.cfox.aidllibrary.bean.UserBean;
import com.cfox.aidlservice.intf.ServiceControl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class ProcessService extends Service{
    private final String TAG = "ProcessService";

    private IBinder iBinder = new MyBinder();

    private String mName = "ProcessService";

    private ProcessUserBean userBean;

    /**
     * 存放客户端回调的list
     */
    RemoteCallbackList<IServerListener> list = new RemoteCallbackList<IServerListener>();

    private class MyBinder extends Stub{

        @Override
        public String getServiceName() throws RemoteException {
            return mName;
        }

        @Override
        public void setName(String name) throws RemoteException {
            mName = name;
        }

        @Override
        public void setProcessUser(ProcessUserBean user) throws RemoteException {
            showUser(user);
            Log.e(TAG,"ProcessService UserInfo setting....");

            int size = list.beginBroadcast();//返回多少个回调监听
            for(int i = 0 ; i < size; i ++){//遍历注册的所有监听
                IServerListener listener = list.getBroadcastItem(i);
                sendUser(listener);
            }
            list.finishBroadcast();

        }

        @Override
        public ProcessUserBean getUser() throws RemoteException {
            Log.e(TAG,"ProcessService UserInfo return....");
            return userBean;
        }

        @Override
        public void registerUserListener(IServerListener listener) throws RemoteException {
            Log.e(TAG,"ProcessService UserInfo registerUserListener....");
            list.register(listener);
        }

        @Override
        public void unregisterUserListener(IServerListener listener) throws RemoteException {
            Log.e(TAG,"ProcessService UserInfo unregisterUserListener....");
            list.unregister(listener);
        }
    }

    private void sendUser(final IServerListener listener){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    listener.addUser(userBean);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        },0,5000);
    }

    private void showUser(ProcessUserBean user){

        String name = user.getName();
        Log.e(TAG,"ProcessService UserInfo Name:" + name);
        int age = user.getAge();
        Log.e(TAG,"ProcessService UserInfo age:" + age);
        List<String> listString =  user.getList();
        for(int i = 0 ; i < listString.size(); i ++){
            Log.e(TAG,"ProcessService UserInfo list:" + listString.get(i));

        }

        Map<String,String> map = user.getMap();
        Set<String> keys = map.keySet();
        for(String key : keys){
            String value = map.get(key);
            Log.e(TAG,"ProcessService UserInfo Map->key:" + key + ";value" + map.get(key));
        }
    }

    @Override
    public void onCreate() {
        Log.e(TAG,"ProcessService onCreate run ......");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"ProcessService onBind run ......");
        return iBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"ProcessService onStartCommand run ......");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.e(TAG,"ProcessService onRebind run ......");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG,"ProcessService onUnbind run ......");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"ProcessService onDestroy run ......");
        super.onDestroy();
    }

}

// IAIDLService.aidl
package com.cfox.aidllibrary;

// Declare any non-default types here with import statements
import com.cfox.aidllibrary.bean.ProcessUserBean;
import com.cfox.aidllibrary.IServerListener;
interface IAIDLService {

    String getServiceName();
    void setName(String name);
    void setProcessUser(in ProcessUserBean user);
    ProcessUserBean getUser();

    void registerUserListener(IServerListener listener);
    void unregisterUserListener(IServerListener listener);

}

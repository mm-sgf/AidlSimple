package com.cfox.aidlservice.intf;

import com.cfox.aidllibrary.bean.UserBean;

public interface ServiceControl {
    void setServiceName(String name);
    String getServiceName();
    void setUser(UserBean user);
    UserBean getUser();
}

// IServerListener.aidl
package com.cfox.aidllibrary;

import com.cfox.aidllibrary.bean.ProcessUserBean;

interface IServerListener {
    void addUser(in ProcessUserBean user);
}

package com.cfox.aidllibrary.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessUserBean  implements Parcelable{

    private String name;
    private int age;
    private Map<String,String> map;
    private List<String> list;

    public ProcessUserBean(){}

    protected ProcessUserBean(Parcel in) {
        name = in.readString();
        age = in.readInt();
        list = in.createStringArrayList();
        map = in.readHashMap(Map.class.getClassLoader());
    }

    public static final Creator<ProcessUserBean> CREATOR = new Creator<ProcessUserBean>() {
        @Override
        public ProcessUserBean createFromParcel(Parcel in) {
            return new ProcessUserBean(in);
        }

        @Override
        public ProcessUserBean[] newArray(int size) {
            return new ProcessUserBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeStringList(list);
        dest.writeMap(map);
    }
}

package com.cfox.aidllibrary.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Map;

public class UserBean implements Parcelable{

    private String name;
    private int age;
    private Dog dog;
    private Map<String,String> map;
    private List<String> list;
    private List<Dog> lsitDog;

    public UserBean(){}

    protected UserBean(Parcel in) {
        name = in.readString();
        age = in.readInt();
        dog = in.readParcelable(Dog.class.getClassLoader());
        list = in.createStringArrayList();
        lsitDog = in.createTypedArrayList(Dog.CREATOR);
        map = in.readHashMap(Thread.currentThread().getContextClassLoader());
    }

    public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
        @Override
        public UserBean createFromParcel(Parcel in) {
            return new UserBean(in);
        }

        @Override
        public UserBean[] newArray(int size) {
            return new UserBean[size];
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

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
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

    public List<Dog> getLsitDog() {
        return lsitDog;
    }

    public void setLsitDog(List<Dog> lsitDog) {
        this.lsitDog = lsitDog;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeParcelable(dog, flags);
        dest.writeStringList(list);
        dest.writeTypedList(lsitDog);
        dest.writeMap(map);
    }
}

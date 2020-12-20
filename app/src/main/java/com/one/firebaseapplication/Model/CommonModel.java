package com.one.firebaseapplication.Model;

import java.lang.reflect.Constructor;
import java.util.Objects;

public class CommonModel {
    private String uId;
    private String Name;
    private String Age;

    public CommonModel(String name ,String age){
        this.Name = name;
        this.Age = age;
    }

    public CommonModel(){

    }
    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    @Override
    public boolean equals(Object obj) {
       if(obj instanceof CommonModel){
           CommonModel cm  = (CommonModel) obj;
           return this.uId.equals(cm.getuId());
       }else {
           return false;
       }
    }


}

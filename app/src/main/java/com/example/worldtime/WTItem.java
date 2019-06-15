package com.example.worldtime;
public class WTItem {
    private int id;
    private String curCity;
    private String curTime;

    public  WTItem(){
        super();
        curCity="";
        curTime="";
    }
    public WTItem(String curTime,String curCity){
        super();
        this.curTime=curTime;
        this.curCity=curCity;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getCurCity(){
        return  curCity;
    }
    public String getCurTime(){
        return  curTime;
    }
    public void setCurCity(String curCity){
        this.curCity=curCity;
    }
    public void setCurTime(String curTime){
        this.curTime=curTime;
    }




}
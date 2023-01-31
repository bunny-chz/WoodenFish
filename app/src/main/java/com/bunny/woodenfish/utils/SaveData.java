package com.bunny.woodenfish.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.bunny.woodenfish.R;

/**
 * Project:  Bunny电子木鱼
 * Comments: SharedPreferences保存键值对工具类
 * JDK version used: <JDK1.8>
 * Create Date：2023-01-30
 * Version: 1.0
 */

public class SaveData {
    private final Context context;
    public SaveData(Context context){
        this.context = context;
    }
    /**
     * SharedPreferences保存 String 键值对
     *
     * @param value 保存的值
     * @param key 保存的键
     */
    public void saveString(String value,String key){
        String name = context.getResources().getString(R.string.SaveData);
        SharedPreferences shp = context.getSharedPreferences(name,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putString(key,value);
        editor.apply();
    }
    /**
     * SharedPreferences 加载 String 键值对
     *
     * @param key 要加载的键
     */
    public String loadString(String key){
        String name = context.getResources().getString(R.string.SaveData);
        SharedPreferences shp = context.getSharedPreferences(name,Context.MODE_PRIVATE);
        return shp.getString(key,null);//默认返回 null
    }
    /**
     * SharedPreferences保存 Boolean 键值对
     *
     * @param value 保存的值
     * @param key 保存的键
     */
    public void saveBoolean(boolean value,String key){
        String name = context.getResources().getString(R.string.SaveData);
        SharedPreferences shp = context.getSharedPreferences(name,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }
    /**
     * SharedPreferences保存 Boolean 键值对
     *
     * @param key 要加载的键
     */
    public boolean loadBoolean(String key){
        String name = context.getResources().getString(R.string.SaveData);
        SharedPreferences shp = context.getSharedPreferences(name,Context.MODE_PRIVATE);
        return shp.getBoolean(key,true);//默认返回 false
    }
    /**
     * SharedPreferences保存 Int 键值对
     *
     * @param value 保存的值
     * @param key 保存的键
     */
    public void saveInt(int value,String key){
        String name = context.getResources().getString(R.string.SaveData);
        SharedPreferences shp = context.getSharedPreferences(name,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putInt(key,value);
        editor.apply();
    }
    /**
     * SharedPreferences保存 Int 键值对
     *
     * @param key 要加载的键
     */
    public int loadInt(String key){
        String name = context.getResources().getString(R.string.SaveData);
        SharedPreferences shp = context.getSharedPreferences(name,Context.MODE_PRIVATE);
        return shp.getInt(key,0);//默认返回 10
    }
}
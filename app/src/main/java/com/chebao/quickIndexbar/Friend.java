package com.chebao.quickIndexbar;

import java.util.Comparator;

/**
 * entity class
 * 同时导入了Comparable(可比较) 接口并重写compareTo方法实现排序功能
 * Created by iwan on 16/8/30.
 */
public class Friend  implements Comparable<Friend>{
    private String name;

    /**
     * 使用成员变量生成构造方法
     * @param name
     */
    public Friend(String name) {
        this.name = name;
        // 一开始就转好拼音
        setPinyin(PinYinUtil.getPinyin(name));
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    private String pinyin;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int compareTo(Friend another) {


        // 排序，A~Z
        return getPinyin().compareTo(another.getPinyin());
    }
}

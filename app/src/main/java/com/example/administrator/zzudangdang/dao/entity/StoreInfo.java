package com.example.administrator.zzudangdang.dao.entity;

/**
 * Created by Administrator on 2017/3/26.
 * 店铺信息
 */

public class StoreInfo {
    private int id;
    private String name;
    private String bosspicture; //商家的图片标识
    private boolean isChoosed;
    private boolean isEditor; //自己对该组的编辑状态
    private boolean ActionBarEditor;// 全局对该组的编辑状态
    private int flag;

    public StoreInfo() {
    }

    public StoreInfo(int id, String name, String bosspicture) {
        this.id = id;
        this.name = name;
        this.bosspicture = bosspicture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    public boolean isEditor() {
        return isEditor;
    }

    public void setEditor(boolean editor) {
        isEditor = editor;
    }
    public boolean isActionBarEditor() {
        return ActionBarEditor;
    }

    public void setActionBarEditor(boolean actionBarEditor) {
        ActionBarEditor = actionBarEditor;
    }

    public String getBosspicture() {
        return bosspicture;
    }

    public void setBosspicture(String bosspicture) {
        this.bosspicture = bosspicture;
    }
}

package com.example.administrator.zzudangdang.dao.entity;

import java.util.List;
import java.util.Map;

/**
 * 向APP返回购物车需要的数据
 */

public class OrderCart {

    private List<StoreInfo> groups; //组元素的列表
    private Map<Integer, List<GoodsInfo>> childs; //子元素的列表

    public OrderCart(List<StoreInfo> groups, Map<Integer, List<GoodsInfo>> childs) {
        this.groups = groups;
        this.childs = childs;
    }

    public OrderCart() {
    }

    public List<StoreInfo> getGroups() {
        return groups;
    }

    public void setGroups(List<StoreInfo> groups) {
        this.groups = groups;
    }

    public Map<Integer, List<GoodsInfo>> getChilds() {
        return childs;
    }

    public void setChilds(Map<Integer, List<GoodsInfo>> childs) {
        this.childs = childs;
    }


}

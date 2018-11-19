package com.jiayang.mvp.mvpframework.widget.pickerview.model;

import java.util.ArrayList;

/**
 * TODO<json数据源>
 *
 * @author: 小嵩
 * @date: 2017/3/16 15:36
 */

public class JsonBean implements IPickerViewData{


    /**
     * name : 省份
     * city : [{"name":"北京市","area":["东城区","西城区","崇文区","宣武区","朝阳区"]}]
     */

    public String name;
    public ArrayList<CityBean> city;



    // 实现 IPickerViewData 接口，
    // 这个用来显示在PickerView上面的字符串，
    // PickerView会通过IPickerViewData获取getPickerViewText方法显示出来。
    @Override
    public String getPickerViewText() {
        return this.name;
    }



    public static class CityBean implements IPickerViewData{
        /**
         * name : 城市
         * area : ["东城区","西城区","崇文区","昌平区"]
         */

        private String name;
        private ArrayList<String> area;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<String> getArea() {
            return area;
        }

        public void setArea(ArrayList<String> area) {
            this.area = area;
        }

        @Override
        public String getPickerViewText() {
            return this.name;
        }
    }
}

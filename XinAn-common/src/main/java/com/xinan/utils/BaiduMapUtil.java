package com.xinan.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.xinan.exception.BaseException;
import com.xinan.properties.BaiduProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
@Data
@AllArgsConstructor
@Slf4j
@Component
public class BaiduMapUtil {

   @Autowired
   private BaiduProperties baiduProperties;

   @Data
    public static class Point{
        private Double lat;
        private Double lng;
        public Point(Double lat, Double lng)
        {
            this.lat = lat;
            this.lng = lng;
        }
        public String toString()
        {
            return lat+","+lng;
        }
    }

    /**
     * 根据具体地址获取地图坐标
     * @return 坐标点
     */
    public Point getPointByAddress(String address)
    {
        //构造请求参数
        Map map = new HashMap();
        map.put("address", address);
        map.put("output","json");
        map.put("ak",baiduProperties.getAk());

        //发起请求,请求地址对应的坐标
        String shopCoordinate = HttpClientUtil.doGet("https://api.map.baidu.com/geocoding/v3", map);

        //解析返回信息
        JSONObject jsonObject = JSON.parseObject(shopCoordinate);
        if(!jsonObject.getString("status").equals("0")){
            throw new BaseException("地址解析失败");
        }

        //数据解析
        JSONObject location = jsonObject.getJSONObject("result").getJSONObject("location");
        Double lat = Double.parseDouble(location.getString("lat"));
        Double lng = Double.parseDouble(location.getString("lng"));

        return new Point(lat,lng);
    }

    /**
     * 根据地图坐标获取两点之间的最短路径距离
     * @param p1 坐标点1
     * @param p2 坐标点2
     * @return 距离 单位m
     */
    public Integer getDistance(Point p1, Point p2)
    {
        String origin = p1.toString();
        String destination = p2.toString();
        Map map = new HashMap();
        map.put("origin", origin);
        map.put("destination", destination);
        map.put("ak",baiduProperties.getAk());
        map.put("steps_info","0");

        //路线规划
        String json = HttpClientUtil.doGet("https://api.map.baidu.com/directionlite/v1/driving", map);

        JSONObject jsonObject = JSON.parseObject(json);
        if(!jsonObject.getString("status").equals("0")){
            throw new BaseException("获取距离失败");
        }

        //数据解析
        JSONObject result = jsonObject.getJSONObject("result");
        JSONArray jsonArray = (JSONArray) result.get("routes");
        return (Integer) ((JSONObject) jsonArray.get(0)).get("distance");
    }
}

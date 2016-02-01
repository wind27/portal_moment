package com.wind.utils;

import com.alibaba.fastjson.JSONObject;
import com.wind.commons.StringUtils;

public class DistanceUtil {

    private final static double PI = 3.14159265358979323; // 圆周率
    private final static double R = 6371229; // 地球的半径

    /**
     * 单纯算距离
     *
     * @param lanlng
     * @param lanlng2
     * @return
     */
    public static double getDistance(String lanlng, String lanlng2) {
        return calcDistance(lanlng, lanlng2);
    }

    /**
     * 附近的人使用
     *
     * @param latlng
     * @param latlng2
     * @return
     */
    public static String getDistanceGeo(String latlng, String latlng2) {

        Double d = calcDistance(latlng, latlng2);

        return d + "km";
    }


    private static Double calcDistance(String latlng, String latlng2) {
        if (!StringUtils.isEmpty(latlng) && !StringUtils.isEmpty(latlng2)) {
            double longt1 = Double.parseDouble(latlng.split(",")[0]);
            double lat1 = Double.parseDouble(latlng.split(",")[1]);

            double longt2 = Double.parseDouble(latlng2.split(",")[0]);
            double lat2 = Double.parseDouble(latlng2.split(",")[1]);
            double x, y, distance;
            x = (longt2 - longt1) * PI * R * Math.cos(((lat1 + lat2) / 2) * PI / 180) / 180;
            y = (lat2 - lat1) * PI * R / 180;
            distance = Math.hypot(x, y);

            java.text.DecimalFormat df = new java.text.DecimalFormat("#.#");
            Double d = Double.parseDouble(df.format(distance));
            return d;
        } else {
            return 0.0;
        }
    }

    //feed距离显示
    public static String convertFinalDistance(String latlng, String latlng2) {

        java.text.DecimalFormat df = new java.text.DecimalFormat("#.#");
        Double d = calcDistance(latlng, latlng2);
        d = d / 1000;

        d = Double.parseDouble(df.format(d));

        if (d < 0.5) {//0.5 km内
            return "0.5km内";
        } else if (d >= 0.5 && d < 1) {
            return "1km内";
        } else {
            return d + "km";
        }

    }


    public static void main(String[] args) {

        System.out.println(calcDistance("39.914635,116.454993", "116.454737,39.914725"));
    }


    public static String correctPosi(HttpUtil httpUtil, String url, String latlng) {
        String lat = latlng.split(",")[1];
        String lng = latlng.split(",")[0];
        url += "&xys=" + lat + "," + lng;

        JSONObject jsonObject = httpUtil.request(url);
        String str = "AMap.MAjaxResult[123]=";

        String result = "";

        if (jsonObject.getBoolean("success") == true) {
            if (jsonObject.get("key") != null
                    && !StringUtils.isEmpty(jsonObject.get("key").toString())) {

                String keyData = jsonObject.get("key").toString();
                String keyDataConverter = keyData.replace(str, "");

                JSONObject vo = JSONObject.parseObject(keyDataConverter);
                result = vo.getString("xys");
                if (result != null && result.length() > 0) {
                    String[] temp = result.split(",");
                    result = "";
                    result = temp[1] + "," + temp[0];
                }

            }

        }

        return result;
    }

}

package utils;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.TravelInfo;

/**
 * Created by tao on 2015/12/14.
 */
public class AppJsonFileReader {

    /**
     * 将Json文件中的内容转成字符串
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(
                    new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 返回解析json数据之后的数据信息
     * @param str
     * @return
     */
    public static List<TravelInfo> setData(String str) {
        try {
            List<TravelInfo> data = new ArrayList<>();
            JSONObject travel = new JSONObject(str);
            JSONArray array = travel.getJSONArray("travelInfo");
            int len = array.length();
            TravelInfo info = null;
            for (int i = 0; i < len; i++){
                info = new TravelInfo();
                JSONObject object = array.getJSONObject(i);
                info.setTravelId(object.getString("travelId"));
                info.setTravelerName(object.getString("travelerName"));
                info.setTitle(object.getString("title"));
                info.setContent(object.getString("content"));
                info.setReadNum(object.getString("readNum"));
                info.setSex(object.getString("sex"));
                info.setAge(object.getString("age"));
                info.setLikeNum(object.getString("likeNum"));
                info.setCommentNum(object.getString("commentNum"));
                data.add(info);
            }
            return data;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}

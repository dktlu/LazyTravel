package http;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import annotation.FormProperty;
import annotation.Ignore;
import result.BookTravelResult;
import result.ScenicResult;

@SuppressLint("SimpleDateFormat")
public class HttpRequestUtil {
    private static final String SCENIC_HOST = "http://apis.baidu.com/";
    private static final String IMG_SYM_HOST = "http://img1.miaotu.com/";

    public static String getServer() {
        return SCENIC_HOST;
    }

    public static String getImgServer() {
        return IMG_SYM_HOST;
    }

    public static final int CONNECTION_TIME_OUT = 20000;

    // private static HttpRequestUtil instance = new HttpRequestUtil();

    // private RestTemplate restTemplate;

    @SuppressWarnings("deprecation")
    private HttpRequestUtil() {
        // Work around pre-Froyo bugs in HTTP connection reuse.
        if (Integer.parseInt(Build.VERSION.SDK) < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");

        }
    }

    public static HttpRequestUtil getInstance() {
        return new HttpRequestUtil();
        // return instance;
    }

    /**
     * 获取实际接口地址
     *
     * @param url
     * @return
     */
    private static String getUrl(String url) {
        return SCENIC_HOST + url;
    }

    /**
     * 获取图片接口地址
     *
     * @param url
     * @return
     */
    private static String getImgUrl(String url) {
        return IMG_SYM_HOST + url;
    }

    /**
     * 将对象属性值转换为MultiValueMap
     *
     * @param o
     * @return
     */
    public List<NameValuePair> transformObject2Map(Object o) {
        return transformObject2Map(o, o.getClass());
    }

    private List<NameValuePair> transformObject2Map(Object o, Class<?> cl) {
        List<NameValuePair> data = new ArrayList<NameValuePair>();
        for (Class<?> superCl = cl.getSuperclass(); superCl != null
                && !superCl.equals(Object.class); superCl = superCl
                .getSuperclass()) {
            data.addAll(transformObject2Map(o, superCl));
        }
        for (Field f : cl.getDeclaredFields()) {
            FormProperty fp = f.getAnnotation(FormProperty.class);
            Ignore ig = f.getAnnotation(Ignore.class);
            boolean accessFlag = f.isAccessible();
            f.setAccessible(true);
            try {
                if (ig != null) {
                    if (ig.byValue()) {
                        if (f.getType().equals(int.class)) {
                            if (f.getInt(o) != ig.intValue()) {
                                data.add(new BasicNameValuePair(fp == null ? f
                                        .getName() : fp.value(), f.get(o)
                                        .toString()));
                            }
                        } else if (f.getType().equals(String.class)) {
                            if (f.get(o) != null
                                    && !f.get(o).equals(ig.stringValue())) {
                                data.add(new BasicNameValuePair(fp == null ? f
                                        .getName() : fp.value(), f.get(o)
                                        .toString()));
                            }
                        }
                    }
                    continue;
                }
                if (fp == null && f.get(o) != null) {
                    data.add(new BasicNameValuePair(f.getName(), f.get(o)
                            .toString()));
                } else if (f.get(o) != null) {
                    data.add(new BasicNameValuePair(fp.value(), f.get(o)
                            .toString()));
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            f.setAccessible(accessFlag);
        }
        return data;
    }

    /**
     * 获取景点的信息
     * @param address
     * @return
     */
    public ScenicResult getScenicInfo(String address) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", address));
        params.add(new BasicNameValuePair("output", "json"));
        return HttpDecoder.getForObject(
                getUrl("apistore/attractions/spot"), ScenicResult.class, params);
    }

    /**
     * 获取热门游记
     * @param address
     * @param page
     * @return
     */
    public BookTravelResult getBookTravel(String address, String page) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("query", address));
        params.add(new BasicNameValuePair("page", page));
        return HttpDecoder.getForObject(
                getUrl("qunartravel/travellist/travellist"), BookTravelResult.class, params);
    }

}
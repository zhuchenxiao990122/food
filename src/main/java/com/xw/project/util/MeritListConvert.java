package com.xw.project.util;

import com.xw.common.util.StringUtil;
import com.xw.project.entity.MeritList;
import com.xw.project.vo.MeritListConvertVo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MeritListConvert {

    public static MeritListConvertVo convert(int first, int size, String donor) throws Exception {
        String url = "http://223.4.77.200:8090/HSZH/rest/hall/statistic/getJZXXSelect?";
        String param = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String endtime = formatter.format(new Date());
        String starttime = "2020-01-01";
        if (StringUtil.isNotEmpty(donor)) {
            param = "jzrname=" + donor.trim() + "&idNo=&starttime=&endtime=&first=" + first + "&second=" + size;
        } else {
            param = "jzrname=&idNo=&starttime=" + starttime + "&endtime=" + endtime + "&first=" + first + "&second=" + size;
        }
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url + param);
        // 通过HttpClient Get请求返回Json数据
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        String json = EntityUtils.toString(entity, "UTF-8").trim();
        JSONObject jsonModelObject = JSONObject.fromObject(json);
        // 通过字典值拿取实体类数据集合curPageDate
        String model = jsonModelObject.getString("data");
        String totalCount = jsonModelObject.getString("totalCount");
        // 转为JSONArray数组
        if (!"null".equals(model)) {
            JSONArray jArray = JSONArray.fromObject(model);
            List<MeritList> strsToList = JSONArray.toList(jArray, new MeritList(), new JsonConfig());
            MeritListConvertVo meritListConvertVo = new MeritListConvertVo();
            meritListConvertVo.setList(strsToList);
            meritListConvertVo.setTotal(Integer.parseInt(totalCount));
            return meritListConvertVo;
        } else {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        MeritListConvertVo res = convert(0, 20, "第三方收到");
        System.out.println(res);
       SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年MM月dd日");
     /*  Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2005-06-09");
       String format = dateformat.format(date);
       System.out.println(dateformat.format(date));*/
    }
}
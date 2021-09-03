import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * @author run
 * @since 2021/4/7 21:11
 */
public class LogisticsTest {



    @Test
    public void test(){
        String url = "https://poll.kuaidi100.com/poll/query.do";
        String customer = "DF457D8FF63DCA399E2B196A479B0133";
        String key = "jakYLkvM8698";
        Map<String, String> param = new HashMap<>();
        param.put("com","shunfeng");
        param.put("num","SF1980785042416");
        param.put("phone","13924434813");

        String sign = JSON.toJSONString(param) + key + customer;

        sign = SecureUtil.md5(sign)
                            .toUpperCase();

        Map<String,String> requestBody = new HashMap<>();
        requestBody.put("customer",customer);
        requestBody.put("sign",sign);
        requestBody.put("param",JSON.toJSONString(param));

        String result = HttpRequest.post(getUrl(url, requestBody))
                                    .header(Header.CONTENT_TYPE,"application/x-www-form-urlencoded")
                                    .execute().body();

        System.out.println(result);


    }

    public static String getUrl(String url, Map<String, String> options){
        StringBuilder sb = new StringBuilder(url);
        sb.append('?');
        options.forEach((key, value) -> sb.append(key).append('=').append(value).append('&'));
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
        return sb.toString();
    }

}

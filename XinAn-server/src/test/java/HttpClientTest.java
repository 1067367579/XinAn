import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest(classes = HttpClientTest.class)
public class HttpClientTest {

    /**
     * 测试用httpClient发送GET请求
     */
    @Test
    public void testGET() throws IOException {
        //创建httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建请求对象
        HttpGet httpGet = new HttpGet("http://localhost:8080/user/shop/status");

        //发送请求 接受响应
        CloseableHttpResponse response = httpClient.execute(httpGet);

        //获取服务端返回的状态码
        int statusCode = response.getStatusLine().getStatusCode();

        //获取响应体
        HttpEntity entity = response.getEntity();
        //需要重新解析为字符串
        String body = EntityUtils.toString(entity);

        System.out.println("返回的响应状态码是: "+statusCode);
        System.out.println("返回的响应数据是: "+body);

        //需要关闭资源
        response.close();
        httpClient.close();
    }

    //发送POST请求
    @Test
    public void testPOST() throws IOException, JSONException {
        //还是创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //接着构建请求对象
        HttpPost httpPost = new HttpPost("http://localhost:8080/admin/employee/login");

        //需要将请求体转化为JSON格式数据
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username","admin");
        jsonObject.put("password","123456");

        //把json对象转化为字符串实体对象
        StringEntity entity = new StringEntity(jsonObject.toString());

        //指定编码格式
        entity.setContentEncoding("utf-8");

        //数据格式
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        //发送
        CloseableHttpResponse response = httpClient.execute(httpPost);

        //解析
        int statusCode = response.getStatusLine().getStatusCode();

        String body = EntityUtils.toString(response.getEntity());

        System.out.println("响应状态码: "+statusCode);
        System.out.println("响应数据为: "+body);

        //关闭资源
        response.close();
        httpClient.close();
    }
}

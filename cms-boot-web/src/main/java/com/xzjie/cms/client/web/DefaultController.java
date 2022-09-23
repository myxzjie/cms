package com.xzjie.cms.client.web;

import com.xzjie.cms.core.utils.JsonUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by xzjie on 2017/8/7.
 */
@Controller
public class DefaultController extends BaseController {


    @RequestMapping(value = {"", "/", "index"})
    public String index(Map<String, Object> model) {
        return getRedirect("/article");
    }

    // https://bing.biturl.top/?resolution=1920&format=json&index=0&mkt=zh-CN
    @GetMapping("/bg.jpg")
    public void getBingImage(HttpServletResponse response) throws IOException {
        // Construct the URL.
        URL url = new URL("https://bing.biturl.top/?resolution=1920&format=json&index=0&mkt=zh-CN");

        // Open the connection.
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//        connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);

        // Receive the JSON response body.
        InputStream stream = connection.getInputStream();
        String res = new Scanner(stream).useDelimiter("\\A").next();

        // Construct the result object.
//        SearchResults results = new SearchResults(new HashMap<String, String>(), response);

        // Extract Bing-related HTTP headers.
//        Map<String, List<String>> headers = connection.getHeaderFields();
//        for (String header : headers.keySet()) {
//            if (header == null) continue;      // may have null key
//            if (header.startsWith("BingAPIs-") || header.startsWith("X-MSEdge-")){
//                results.relevantHeaders.put(header, headers.get(header).get(0));
//            }
//        }
        Map<String, String> map = JsonUtils.toObject(res, Map.class);
        stream.close();

        URL urlImage = new URL(map.get("url"));
        BufferedImage image = ImageIO.read(urlImage);

        //写给浏览器
        response.setContentType("image/jpeg");
        //浏览器不要缓存
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    @RequestMapping(value = "demo")
    public String demo(){
        return getRemoteView("demo");
    }
}

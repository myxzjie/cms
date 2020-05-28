package com.xzjie.cms.system.web;

import com.xzjie.cms.core.utils.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/hot")
public class HotController {
    List<String> datas = new ArrayList<>();

    public HotController() {
        datas.add("小绿瓶精华");
        datas.add("白牡丹");
    }

    @GetMapping("add")
    public Map<String, Object> add(String data) {
        datas.add(data);
        return MapUtils.create().set("code", 0);
    }

    @RequestMapping(value = "/data", method = RequestMethod.HEAD)
    public void headhotData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String latest_ETags = System.currentTimeMillis() + "";
        String old_ETags = request.getHeader("If-None-Match");
        log.info("head请求，old_ETags=" + old_ETags);
//        if (latest_ETags.equals("") || !latest_ETags.equals(old_ETags)) {
//
//        }
        response.setHeader("Etag", latest_ETags);
    }

    @GetMapping("/data")
    public String hotData(HttpServletRequest request) {

        String old_ETags = request.getHeader("If-None-Match");
        log.info("get请求，old_ETags=" + old_ETags);

        StringBuilder hotWord = new StringBuilder();
        datas.forEach(word -> {
            hotWord.append(word);
            hotWord.append("\r\n");
        });
        return hotWord.toString();
    }
}

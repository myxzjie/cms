package com.xzjie.cms.configure;

import lombok.Setter;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;

@Setter
@Configuration
@ConfigurationProperties(prefix = "location.temp")
public class LocationProperties {
    private boolean user;
    private String directory;

    @PostConstruct
    private void init() {
        String location = getLocationDirectory();
        File tmpFile = new File(location);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
    }

    @Bean
    public String getLocationDirectory() {
        String location;
        if (user) {
            location = System.getProperty("user.home") + directory;
        } else {
            location = directory;
        }
        return location;
    }

    public String getWechatFilePath() {
        String location = getLocationDirectory();
        String fileName = System.currentTimeMillis() + RandomUtils.nextInt(4) + ".png";
        return location + "/wechat/" + fileName;
    }
//
//    private String getFilePath(String directory) {
////        LocalDateTime localDate = LocalDateTime.now();
////        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//
//        //4.把 2019-01-01  转成  2019/01/01
////        String format = localDate.format(dtf);
//        String location = getLocationDirectory();
//        String fileName = System.currentTimeMillis() + RandomUtils.nextInt(4) + ".png";
//        String path = directory + fileName;
//        return location + path;
//    }
}

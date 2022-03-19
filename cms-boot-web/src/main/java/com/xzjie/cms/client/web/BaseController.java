package com.xzjie.cms.client.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@ControllerAdvice
public abstract class BaseController {
    @Autowired
    protected HttpServletRequest request;


    @Value(value = "${template.name}")
    private String template;


    /**
     * 获得前端路由前缀
     *
     * @param view
     * @return
     */
    protected String getRemoteView(String view) {

//        Site site = SystemCacheManager.get(ConstantsUtils.SITE_ID_KEY + getSiteId(), Site.class);
        String remote = "";
//        if (site != null && StringUtils.isNotBlank(site.getTheme())) {
//            remote = site.getTheme() + "/" + view;
//        } else {
        remote = template + "/" + view;
//        }
        return remote;
    }

    public String getForward(String url) {
        return "forward:" + url;
    }

    public String getRedirect(String url) {
        return "redirect:" + url;
    }

//    public Long getSiteId() {
//        String cid = request.getHeader(ConstantsUtils.ET_CID);
//
//        if (cid == null) {
//            cid = request.getParameter(ConstantsUtils.CID);
//        }
//
//        if (cid == null) {
//            cid = ConstantsUtils.SITE_ID_DEFAULT;
//        }
//        return Long.parseLong(cid);
//
//    }

    public Session getSession() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null) {
                session = subject.getSession();
            }
            if (session != null) {
                return session;
            }
        } catch (InvalidSessionException e) {

        }
        return null;
    }

    /**
     * 获取授权主要对象
     */
    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * Date格式化字符串
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * DateTime格式化字符串
     */
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * Time格式化字符串
     */
    private static final String TIME_FORMAT = "HH:mm:ss";

//    @InitBinder
//    protected void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) throws IllegalArgumentException {
//                setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern(DATE_FORMAT)));
//            }
//        });
//        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) throws IllegalArgumentException {
//                setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern(DATETIME_FORMAT)));
//            }
//        });
//        binder.registerCustomEditor(LocalTime.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) throws IllegalArgumentException {
//                setValue(LocalTime.parse(text, DateTimeFormatter.ofPattern(TIME_FORMAT)));
//            }
//        });
//        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) throws IllegalArgumentException {
//                SimpleDateFormat formatter = new SimpleDateFormat(DATETIME_FORMAT);
//                try {
//                    setValue(formatter.parse(text));
//                } catch (Exception e) {
//                    throw new RuntimeException(String.format("Error parsing %s to Date", text));
//                }
//            }
//        });
//    }
}

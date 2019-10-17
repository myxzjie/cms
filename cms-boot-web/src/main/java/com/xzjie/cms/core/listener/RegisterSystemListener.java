package com.xzjie.cms.core.listener;

import com.xzjie.cms.core.event.EmailEvent;
import com.xzjie.cms.core.event.SystemLogEvent;
import com.xzjie.cms.model.SystemLog;
import com.xzjie.cms.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RegisterSystemListener {
    @Value("${mail.from}")
    private String from;

    @Value("${mail.from-name}")
    private String fromName;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SystemLogService systemLogService;

    @Async
    @Order
    @EventListener(SystemLogEvent.class)
    public void systemLogListener(SystemLogEvent event) {
        SystemLog log = (SystemLog) event.getSource();
        log.setCreateDate(LocalDateTime.now());
        //保存
        systemLogService.save(log);
    }


    @Async
    @Order
    @EventListener(EmailEvent.class)
    public void emailListener(EmailEvent event) {
        MimeMessagePreparator mailMessage = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
            message.setFrom(from, fromName);
            for (String to : event.getTos()) {
                message.addTo(to);
            }
            message.setSubject(event.getSubject());
            message.setText(event.getContent(), true);
//            String html = "<html><body><h4>Hello,SpringBoot</h4><img src='cid:boot' /></body></html>";
//            mimeMessageHelper.setText(html, true);
            // 设置内嵌元素 cid，第一个参数表示内联图片的标识符，第二个参数标识资源引用
//            mimeMessageHelper.addInline("boot", new ClassPathResource("public/images/boot.png"));
            //添加附件,第一个参数表示添加到 Email 中附件的名称，第二个参数是图片资源
        };

        mailSender.send(mailMessage);
    }

}

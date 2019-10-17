package com.xzjie.cms.core.event;

import com.xzjie.cms.dto.UserResponse;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.ArrayList;
import java.util.List;

@Getter
public class EmailEvent extends ApplicationEvent {

    private List<String> tos;
    private String subject;
    private String content;

    public EmailEvent(Object source) {
        super(source);
    }

    public EmailEvent(Object source, List<String> tos, String subject, String content) {
        super(source);
        this.tos = tos;
        this.subject = subject;
        this.content = content;
    }

    public static class EmailEventBuilder {
        private List<String> tos;
        private String subject;
        private String content;

        public EmailEventBuilder setTos(List<String> tos) {
            this.tos = tos;
            return this;
        }

        public EmailEventBuilder setTo(String to) {
            if (this.tos == null) {
                this.tos = new ArrayList<>();
            }
            this.tos.add(to);
            return this;
        }

        public EmailEventBuilder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public EmailEventBuilder setContent(String content) {
            this.content = content;
            return this;
        }

        public static EmailEventBuilder builder() {
            return new EmailEventBuilder();
        }

        public EmailEvent build() {
            return new EmailEvent(new Object(), tos, subject, content);
        }
    }
}

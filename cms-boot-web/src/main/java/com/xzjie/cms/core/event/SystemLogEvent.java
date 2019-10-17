package com.xzjie.cms.core.event;

import org.springframework.context.ApplicationEvent;

public class SystemLogEvent extends ApplicationEvent {
    public SystemLogEvent(Object source) {
        super(source);
    }
}

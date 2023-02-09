package com.poc.tutorials.event;

import com.poc.tutorials.model.ActionType;
import com.poc.tutorials.model.TutorialEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuditPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishCustomEvent(final TutorialEntity entity, ActionType actionType) {
        log.info("Publishing audit event. ");
        AuditEvent auditEvent = new AuditEvent(this, entity, actionType);
        applicationEventPublisher.publishEvent(auditEvent);
    }
}

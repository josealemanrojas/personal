package com.poc.tutorials.event;

import com.poc.tutorials.model.ActionType;
import com.poc.tutorials.model.TutorialEntity;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

@Getter
@ToString
public class AuditEvent extends ApplicationEvent {
    @ToString.Include
    private TutorialEntity tutorialEntity;
    @ToString.Include
    private ActionType actionType;

    public AuditEvent(Object source, TutorialEntity entity, ActionType actionType) {
        super(source);
        this.tutorialEntity = entity;
        this.actionType = actionType;
    }
}

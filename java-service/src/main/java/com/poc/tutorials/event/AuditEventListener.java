package com.poc.tutorials.event;

import com.poc.tutorials.model.TutorialDocument;
import com.poc.tutorials.model.TutorialEntity;
import com.poc.tutorials.repository.TutorialDocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class AuditEventListener implements ApplicationListener<AuditEvent> {

    @Autowired
    private TutorialDocumentRepository tutorialDocumentRepository;

    @Override
    public void onApplicationEvent(AuditEvent event) {
        log.info("Saving to history {}", event);
        TutorialEntity entity = event.getTutorialEntity();

        TutorialDocument document = new TutorialDocument()
                .actionType(event.getActionType())
                .published(entity.isPublished())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .createdAt(LocalDateTime.now())
                .tutorialId(entity.getId());

        tutorialDocumentRepository.save(document);
    }
}

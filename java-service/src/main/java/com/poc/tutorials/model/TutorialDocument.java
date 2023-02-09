package com.poc.tutorials.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document("TutorialDocument")
@Setter
@NoArgsConstructor
@Getter
@Accessors(fluent = true)
public class TutorialDocument {

    @Id
    private String id;

    private UUID tutorialId;
    private String title;
    private String description;
    private boolean published;
    private ActionType actionType;


}


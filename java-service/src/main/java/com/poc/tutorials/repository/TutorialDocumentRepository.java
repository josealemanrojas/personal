package com.poc.tutorials.repository;

import com.poc.tutorials.model.TutorialDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TutorialDocumentRepository extends MongoRepository<TutorialDocument, String> {

}

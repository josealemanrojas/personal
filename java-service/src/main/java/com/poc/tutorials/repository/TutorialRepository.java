package com.poc.tutorials.repository;

import com.poc.tutorials.model.TutorialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TutorialRepository
        extends JpaRepository<TutorialEntity, UUID>, JpaSpecificationExecutor<TutorialEntity> {
    List<TutorialEntity> findByPublished(boolean published);

    List<TutorialEntity> findByTitleContaining(String title);
}

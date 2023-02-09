package com.poc.tutorials.service;

import com.poc.tutorials.model.TutorialEntity;
import com.poc.tutorials.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Optional;


@Service
@Transactional
public class TutorialService {

  @Autowired private TutorialRepository tutorialRepository;

  @CacheEvict(cacheNames = "tutorials", allEntries = true)
  public TutorialEntity createTutorial(TutorialEntity tutorialEntity) {
    return tutorialRepository.save(new TutorialEntity(tutorialEntity.getTitle(), tutorialEntity.getDescription(), tutorialEntity.isPublished()));
  }

  @CacheEvict(cacheNames = "tutorials", allEntries = true)
  public TutorialEntity updateTutorialById(UUID tutorialId, TutorialEntity tutorialEntity) {
    return tutorialRepository.save(tutorialEntity);
  }

  @Caching(evict = {@CacheEvict(cacheNames = "tutorials", allEntries = true) })
  public void deleteTutorialById(UUID tutorialId) {
    tutorialRepository.deleteById(tutorialId);
  }

  public void deleteAllTutorials(TutorialEntity tutorialEntity) {
    tutorialRepository.deleteAll();
  }

  public Optional<TutorialEntity>  getTutorialById(UUID tutorialId) {
    return  tutorialRepository.findById(tutorialId);
  }

  @Cacheable(value = "tutorials")
  public List<TutorialEntity> getAllTutorialsByTitle(String title) {

    List<TutorialEntity> tutorials = new ArrayList<TutorialEntity>();

    if (title == null)
      tutorialRepository.findAll().forEach(tutorials::add);
    else
      tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

    return tutorials;
  }

  @CachePut(value="published", condition="#tutorialEntity.published==True")
  //@CachePut(value="addresses", unless="#result.length()<64")
  public List<TutorialEntity> getTutorialsPublished() {
    return tutorialRepository.findByPublished(true);
  }


  public void deleteTutorial(UUID id) {
    tutorialRepository.deleteById(id);
  }

  public void deleteAllTutorials() {
    tutorialRepository.deleteAll();
  }
}

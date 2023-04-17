package com.example.poc.service

import com.example.poc.model.TutorialEntity
import com.example.poc.repository.TutorialRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Supplier

@Service
class TutorialService {
    @Autowired
    lateinit var tutorialRepository: TutorialRepository

    fun createTutorial(tutorialEntity: TutorialEntity) : TutorialEntity {
        return tutorialRepository.save(tutorialEntity)
    }

    fun updateTutorialById(tutorialId : UUID, tutorialEntity: TutorialEntity) : TutorialEntity{
        return tutorialRepository.save( TutorialEntity(tutorialId, tutorialEntity.title, tutorialEntity.description, tutorialEntity.published))
    }

    fun deleteTutorialById(id: UUID) : Boolean {
        return tutorialRepository.findById(id)
            .map (tutorialRepository::delete)
            .map { true }
            .orElse(false)
    }

    fun deleteAllTutorials() {
        tutorialRepository.deleteAll()
    }

    fun getTutorialById(id : UUID) : Optional<TutorialEntity> {
        return tutorialRepository.findById(id)
    }

    fun getAllTutorialsByTitle(title: String) : List<TutorialEntity>
    {

        return  Optional.ofNullable(title)
            .map {  tutorialRepository.findByTitleContaining(it)}
            .orElseGet(Supplier{ tutorialRepository.findAll() })

    }

    fun getTutorialsPublished() : List<TutorialEntity> {
        return tutorialRepository.findByPublished(true)
    }

    fun deleteTutorial(id : UUID) {
        tutorialRepository.deleteById(id)
    }

}
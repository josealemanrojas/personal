package com.example.poc.controller

import com.example.poc.model.TutorialEntity
import com.example.poc.service.TutorialService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.function.Supplier


@RestController
@RequestMapping("/api/tutorials")
@CrossOrigin("*")
class TutorialController {

    @Autowired
    private lateinit var tutorialService: TutorialService


    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<TutorialEntity>? {
        return tutorialService.getTutorialById(id)
            .map { ResponseEntity.ok(it) }
            .orElseGet(Supplier { ResponseEntity.status(HttpStatus.NOT_FOUND).build() })
    }

    @PostMapping
    fun save(@Valid @RequestBody tutorialEntity: TutorialEntity): ResponseEntity<TutorialEntity> {
        return ResponseEntity<TutorialEntity>(tutorialService.createTutorial(tutorialEntity), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: UUID): HttpStatus? {
        return tutorialService.getTutorialById(id)
            .map { tutorialService.deleteTutorial(it.id) }
            .map { HttpStatus.ACCEPTED }
            .orElseGet(Supplier { HttpStatus.NOT_FOUND })
    }

    @DeleteMapping("/deleteAll")
    fun deleteAll(): HttpStatus? {
        tutorialService.deleteAllTutorials()
        return HttpStatus.ACCEPTED
    }

    @PatchMapping("/{id}")
    fun updateTutorial(
        @PathVariable id: UUID,
        @Valid @RequestBody tutorialEntity: TutorialEntity
    ): ResponseEntity<TutorialEntity> {
        return ResponseEntity.ok(tutorialService.updateTutorialById(id, tutorialEntity))
    }

    @GetMapping("/all")
    fun getAllTutorialsByTitle(@RequestParam(required = false) title: String?): ResponseEntity<List<TutorialEntity>> {
        return ResponseEntity.ok( tutorialService.getAllTutorialsByTitle(title.orEmpty()) )
    }

    @GetMapping("/published")
    fun getTutorialsPublished(): ResponseEntity<List<TutorialEntity>> {
        return ResponseEntity.ok(tutorialService.getTutorialsPublished())
    }
}
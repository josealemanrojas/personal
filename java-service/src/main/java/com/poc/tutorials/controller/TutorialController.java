package com.poc.tutorials.controller;

import com.poc.tutorials.model.TutorialEntity;
import com.poc.tutorials.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/tutorials")
public class TutorialController {
    @Autowired
    private TutorialService tutorialService;

    /**
     * Adds a tutorial.
     */
    @PostMapping("/")
    public ResponseEntity<TutorialEntity> createTutorial(
            @Valid @RequestBody TutorialEntity tutorialEntity) {
        return ResponseEntity.ok(tutorialService.createTutorial(tutorialEntity));
    }

    /**
     * Delete a tutorial
     */
    @DeleteMapping(value = "/{tutorialId}")
    public ResponseEntity<HttpStatus>  deleteTutorial(@PathVariable("tutorialId") UUID tutorialId) {
      tutorialService.deleteTutorialById(tutorialId);
      return ResponseEntity.ok( HttpStatus.ACCEPTED);
    }
   /**
   * Delete all tutorials
   *
   */
  @DeleteMapping(value = "/deleteAll")
  public ResponseEntity<HttpStatus>  deleteAllTutorial() {
    tutorialService.deleteAllTutorials();
    return ResponseEntity.ok( HttpStatus.ACCEPTED);
  }
    /**
     * Updates a tutorial
     */
    @PatchMapping(value = "/{tutorialId}")
    public ResponseEntity<TutorialEntity> updateTutorial(
            @PathVariable("tutorialId") UUID tutorialId,
            @Valid @RequestBody TutorialEntity tutorialEntity) {
        return ResponseEntity.ok(tutorialService.updateTutorialById(tutorialId, tutorialEntity));
    }

    /**
     * Get a tutorial by id
     */
    @GetMapping(value = "/{tutorialId}")
    public ResponseEntity<TutorialEntity> getTutorialById(
            @PathVariable("tutorialId") UUID tutorialId) {
        return tutorialService.getTutorialById(tutorialId)
                .map(t -> ResponseEntity.ok(t))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
  /**
   * Get all tutorials
   *
   */
  @GetMapping(value = "/all")
  public ResponseEntity<List<TutorialEntity>> getAllTutorials(
      @RequestParam(required = false)  String title) {
    System.out.println("Getting tutorials by title:  " + title);
    return ResponseEntity.ok(tutorialService.getAllTutorialsByTitle(title));
  }


    /**
     * Get tutorials published
     */
    
     @GetMapping(value = "/published")
    public ResponseEntity<List<TutorialEntity>> getTutorialsPublished() {
        List<TutorialEntity> tutorialsPublished = tutorialService.getTutorialsPublished();
        return tutorialsPublished.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                :
                ResponseEntity.ok(tutorialsPublished);
    }
}
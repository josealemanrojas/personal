package com.example.poc.repository

import com.example.poc.model.TutorialEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TutorialRepository : JpaRepository<TutorialEntity, UUID>, JpaSpecificationExecutor<TutorialEntity> {
    fun findByPublished(published: Boolean) : List<TutorialEntity>
    fun findByTitleContaining(title: String) : List<TutorialEntity>
}
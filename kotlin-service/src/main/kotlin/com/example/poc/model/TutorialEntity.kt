package com.example.poc.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.UUID


@Entity(name = "tutorial")
@Table(schema = "tutorials", name = "tutorial")
data class TutorialEntity(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        @Column(
                name = "id",
                nullable = false,
                insertable = false,
                updatable = false,
                columnDefinition = "UUID"
        )
        val id: UUID = UUID.randomUUID(),
        @Column(name = "title", nullable = false)
        val title: String = "",
        @Column(name = "description")
        val description: String = "",
        @Column(name = "published")
        val published: Boolean = false, ) {}

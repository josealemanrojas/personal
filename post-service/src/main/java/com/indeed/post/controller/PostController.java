package com.indeed.post.controller;

import com.indeed.post.model.Author;
import com.indeed.post.model.Post;
import com.indeed.post.repository.PostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostController {

    @Autowired
    PostRepository postRepository;

    @QueryMapping
    public List<Post> recentPosts(@Argument int count, @Argument int offset) {
        return postRepository.findAll().subList(offset , offset + count);
    }

    @MutationMapping
    public Post createPost(@Argument String title, @Argument String text,
                           @Argument String authorId) {
        UUID id = UUID.randomUUID();
        Post post = Post.builder()
                .id(id)
                .title(title)
                .content(text)
                .published(true)
                .author(Author.builder()
                        .id(UUID.fromString(authorId))
                        .name("Author for " + id)
                        .build())
                .build();

        return postRepository.save(post);
    }
}

package com.indeed.post;

import com.indeed.post.model.Author;
import com.indeed.post.model.Post;
import com.indeed.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootApplication
public class PostServiceApplication implements CommandLineRunner {

    @Autowired
    PostRepository postRepository;

    public static void main(String[] args) {
        System.out.println("starting post service app");
        SpringApplication.run(PostServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        postRepository.deleteAll();
        IntStream.range(1,10)
                .boxed()
                .forEach(index -> {
                    UUID id = UUID.randomUUID();
                    Post post = Post.builder()
                            .id(id)
                            .content("Content for" + id)
                            .published(index % 2 == 0)
                            .title("Title for " + id)
                            .author(Author.builder()
                                    .id(UUID.randomUUID())
                                    .name("Author for " + id)
                                    .build())
                            .build();
                    postRepository.save(post);
                });
    }
}

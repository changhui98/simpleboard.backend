package com.example.prac.service;

import com.example.prac.model.Post;
import com.example.prac.repository.PostRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Optional<Post> getPost(Long id) {
        return postRepository.findById(id);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> updatePost(Long id, Post updatePost) {

        return postRepository.findById(id).map(p ->{
            p.setTitle(updatePost.getTitle());
            p.setContent(updatePost.getContent());
            return postRepository.save(p);
        });
    }

    public boolean deletePost(Long id) {

        return postRepository.findById(id).map(p -> {
            postRepository.delete(p);
            return true;
        }).orElse(false);
    }

}

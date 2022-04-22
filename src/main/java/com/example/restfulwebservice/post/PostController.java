package com.example.restfulwebservice.post;

import com.example.restfulwebservice.user.User;
import com.example.restfulwebservice.user.UserNotFoundException;
import com.example.restfulwebservice.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequiredArgsConstructor
@RequestMapping("/jpa")
@RestController
public class PostController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostsByUser(@PathVariable int id){
        Optional<User> maybe_user = userRepository.findById(id);

        if(!maybe_user.isPresent()){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        User user = maybe_user.get();

        return user.getPosts();
    }

    @GetMapping("/users/{uid}/posts/{pid}")
    public EntityModel<Post> retrievePostByUser(@PathVariable int uid, @PathVariable int pid){
        Optional<User> maybeUser = userRepository.findById(uid);

        if(!maybeUser.isPresent()){
            throw new UserNotFoundException(String.format("ID[%s] not found", uid));
        }

        Post post = postRepository.getById(pid);

        EntityModel entityModel = EntityModel.of(post);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllPostsByUser(uid));
        entityModel.add(linkTo.withRel("all-posts"));

        return entityModel;
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable int id, @RequestBody Post post){
        Optional<User> maybe_user = userRepository.findById(id);

        if(!maybe_user.isPresent()){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        post.setUser(maybe_user.get());
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{uid}/posts/{pid}")
    public void deleteUser(@PathVariable int uid, @PathVariable int pid){
        Optional<User> maybeUser = userRepository.findById(uid);

        if(!maybeUser.isPresent()){
            throw new UserNotFoundException(String.format("ID[%s] not found", uid));
        }

        postRepository.deleteById(pid);
    }

}


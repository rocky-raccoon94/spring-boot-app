package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(path = "/posts")
public class PostCont {
    private final PostRepo postRepo;

    @Autowired
    PostCont(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @GetMapping("/all")
    public Iterable<Post> getAll() {
        return postRepo.findAll();
    }

    @Cacheable(value = "posts", key = "#Personid")
    @GetMapping("/{Personid}")
    public Optional<Post> getById(@PathVariable long Personid) {
        log.info(postRepo.findById(Personid).toString());
        return postRepo.findById(Personid);
    }

    @PutMapping(value = "/{Personid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePost(@PathVariable long Personid, @RequestBody Post tmp) {
        var obj = postRepo.findById(Personid);
        Post post = obj.orElse(null);
        if (post == null)
            return;
        post.setPost(tmp.getPost());
        post.setLastname(post.getLastname());
        post.setFirstname(post.getFirstname());
        postRepo.save(post);
    }

    @PostMapping("/addpost")
    public @ResponseBody
    String addPost(@RequestBody Post post) {
        postRepo.save(new Post(post.getLastname(), post.getFirstname(), post.getPost()));
        return post.toString();
    }

    @DeleteMapping("/deletePost/{Personid}")
    public String deletePost(@PathVariable long Personid) {
        try {
            postRepo.deleteById(Personid);
            return "Deleted";
        } catch (Exception e) {
            return "id not found";
        }
    }
}

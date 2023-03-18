package com.springboot.blog.service.impl;


import com.springboot.blog.entity.post;
import com.springboot.blog.exceptions.resourcenotfound;
import com.springboot.blog.payload.postdto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.postservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class postserviceimp implements postservice {
    private PostRepository postRepository;

    public postserviceimp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public postdto createpost(postdto postdto){
        post post=maptoentity(postdto);





       post newpost= postRepository.save(post);



        postdto postresponse=maptodto(newpost);


        return postresponse;
    }

    @Override
    public List<postdto> getallpost() {
       List<post>posts=postRepository.findAll();
       return  posts.stream().map(post -> maptodto(post)).collect(Collectors.toList());
    }

    @Override
    public postdto getpostbyid(long id) {
        post post =postRepository.findById(id).orElseThrow(()->new resourcenotfound("post","id",id));
        return maptodto(post);
    }

    @Override
    public postdto updatepost(postdto postdto, long id) {
        post post =postRepository.findById(id).orElseThrow(()->new resourcenotfound("post","id",id));
        post.setTitle(postdto.getTitle());
        post.setContent(postdto.getContent());
        post.setDescription(postdto.getDescription());
        post updatepost=postRepository.save(post);
        return maptodto(updatepost);


    }

    private postdto maptodto(post post){

        postdto postdto=new postdto();
        postdto.setId(post.getId());
        postdto.setTitle(post.getTitle());
        postdto.setDescription(post.getDescription());
        postdto.setContent(post.getContent());
return postdto;

    }
    private post maptoentity(postdto postdto){
        post post=new post();
        post.setTitle(postdto.getTitle());
        post.setDescription(postdto.getDescription());
        post.setContent(postdto.getContent());
return post;
    }

}

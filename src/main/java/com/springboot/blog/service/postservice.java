package com.springboot.blog.service;

import com.springboot.blog.payload.postdto;

import java.util.List;

public interface postservice {
    postdto createpost(postdto postdto);
    List<postdto> getallpost();
    postdto getpostbyid(long id);
    postdto updatepost(postdto postdto,long id);
}

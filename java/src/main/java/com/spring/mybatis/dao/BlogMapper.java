package com.spring.mybatis.dao;

import com.spring.model.Blog;

public interface BlogMapper {

    Blog selectBlogAuthor(Integer bid);

    Blog selectBlogAuthor2(Integer bid);

    Blog selectBlogPosts(Integer bid);
}

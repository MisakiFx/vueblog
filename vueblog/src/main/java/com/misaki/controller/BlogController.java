package com.misaki.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.misaki.common.lang.Result;
import com.misaki.entity.Blog;
import com.misaki.service.BlogService;
import com.misaki.shiro.AccountProfile;
import com.misaki.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){

        Page page = new Page(currentPage,5);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.success(pageData);
    }

    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id")Long id){

        Blog blog = blogService.getById(id);
        Assert.notNull(blog,"the artical is not exist");
        return Result.success(blog);
    }

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog){
        Blog temp = null;
        Long currentUserId = ShiroUtils.getProfile().getId();
        if(blog.getId()!=null){
            temp = blogService.getById(blog.getId());
            Assert.isTrue(temp.getUserId().longValue()==currentUserId,"no edit permission");
        }else {
            temp = new Blog();
            temp.setUserId(currentUserId);
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        BeanUtil.copyProperties(blog,temp,"id","userId","created","status");

        blogService.saveOrUpdate(temp);
        return Result.success("release success");
    }

    @GetMapping("/blog/delete/{id}")
    public Result delete(@PathVariable(name = "id")String  id){
        boolean result = blogService.removeById(id);// 据库中为Long id
        Assert.isTrue(result,"本文章已被删除");
        return Result.success("成功删除本文章");
    }

    @GetMapping("/blog/findByTitle")
    public Result findByTitle(@RequestParam(defaultValue = "") String title){
        List<Blog> blogs = blogService.list(new QueryWrapper<Blog>()
                .like("title", title)
                .orderByDesc("created"));
        Assert.notNull(blogs,"未查询到指定文章");
        return Result.success("成功查询到指定文章", blogs);
    }
}

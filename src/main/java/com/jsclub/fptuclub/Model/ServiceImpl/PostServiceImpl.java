package com.jsclub.fptuclub.Model.ServiceImpl;

import com.jsclub.fptuclub.Model.Entity.Post;
import com.jsclub.fptuclub.Model.Repository.PostRepository;
import com.jsclub.fptuclub.Model.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	PostRepository postRepository;
	@Override
	public Post findPostById(int postID) {
		return null;
	}

	@Override
	public void savePost(Post post) {

	}
}

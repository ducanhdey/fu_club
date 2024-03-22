package com.jsclub.fptuclub.Model.Service;

import com.jsclub.fptuclub.Model.Entity.Post;

public interface PostService {
	Post findPostById(int postID);
	void savePost(Post post);

}

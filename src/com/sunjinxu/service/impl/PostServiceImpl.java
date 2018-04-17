package com.sunjinxu.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunjinxu.mapper.PostMapper;
import com.sunjinxu.pojo.Post;
import com.sunjinxu.service.PostService;
import com.sunjinxu.service.UserService;
import com.sunjinxu.tools.PageInfo;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostMapper postMapper;

	@Autowired
	UserService userService;

	@Override
	public void insert(Post post) {
		try {
			post.setStatus(2);
			post.setCreate_at(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
					.format(new Date()));
			post.setUpdate_at(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
					.format(new Date()));
			System.out.println("插入测试1" + post);
			String tagName = post.getTagName();
			Integer i = postMapper.checkTag(tagName);
			if (i.equals(0)) {
				postMapper.insertTag(post);
				post.setTagId(post.getId());
			} else {
				postMapper.updateTagFrequencyUp(tagName);
				post.setTagId(postMapper.getTagId(tagName));
			}
			postMapper.insert(post);
			post.setPostId(post.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertAttributes(Post post) {
		try {
			postMapper.insertPostTag(post);
			postMapper.insertAttributes(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Post post) {
		try {
			postMapper.update(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Post post) {
		try {
			Integer postId = post.getPostId();
			System.out.println("====文章ID====" + postId);
			int tagId = postMapper.getTagIdByPostId(postId);
			postMapper.deleteAttr(postId); // 删除文章属性
			postMapper.deletePostTag(postId); // 删除文章tag关系
			if (postMapper.countTags() > 1) { // 删除tag
				postMapper.updateTagFrequencyDown(tagId);
			} else {
				postMapper.deleteTag0(tagId);
			}
			postMapper.delete(postId); // 删除文章
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Post get(int postId,String ip) {
		try {
			long time = System.currentTimeMillis();	//获取当前时间戳
			String viewTime = String.valueOf(time);	
			Integer count =postMapper.countPview(postId,ip);//检查当前IP是否看过这篇文章
			if (count.equals(0)) {	//若为看过，则更新这篇文章view并插入浏览记录
				postMapper.updateView(postId);
				postMapper.insertPview(postId, viewTime, ip);
			}else {	//若看过，检查最新记录浏览时间
				long oldtime = Long.parseLong(postMapper.getPviewTime(postId, ip));
				if ((time-oldtime)/(1000*60)>=10) {	//若超过10分钟，则更新这篇文章view并更新浏览记录
					postMapper.updateView(postId);
					postMapper.updatePview(postId, viewTime, ip);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return postMapper.get(postId);
	}

	@Override
	public Post getFullOne(int id) {
		return postMapper.getFullOne(id);
	};

	// 标签云
	@Override
	public Map<String, Double> findTagCloud() throws Exception {
		int limit = 200;
		int tag_level;
		int tagNums = postMapper.countTags();
		if (tagNums < 15) {
			tag_level = tagNums;
		} else {
			tag_level = 15;
		}
		Map<String, Double> tagCloud = new LinkedHashMap<String, Double>();
		List<Map<String, String>> tagList = postMapper.findAllTagByDesc(limit);
		int total = tagList.size();
		double stepper = Math.ceil(total / tag_level); // 算出每个档次应该放几个标签
		int counter = 1;
		if (total > 0) {
			for (Map<String, String> map : tagList) {
				double weight = Math.ceil(counter / stepper) + 1;
				tagCloud.put(map.get("tagName"), weight);
				counter++;
			} // 数组键名为标签，值为归入的档次
		}
		return tagCloud;
	}

	@Override
	public List<Object> findAllType() {
		List<Object> types = new ArrayList<Object>();
		types = postMapper.findAllType();
		return types;
	}

	@Override
	public PageInfo<Post> postList(int pageNum, int pageSize) {
		int start = pageSize * (pageNum - 1);
		List<Post> posts = postMapper.list(start, pageSize);
		System.out.println("===文章列表2==="+posts);
		
		
		PageInfo<Post> pageInfo = new PageInfo<Post>(
				postMapper.list(start, pageSize), postMapper.total(), pageNum,
				pageSize);
		return pageInfo;
	}

	@Override
	public PageInfo<Post> searchPostList(int pageNum, int pageSize,
			String title) {
		int start = pageSize * (pageNum - 1);
		PageInfo<Post> pageInfo = new PageInfo<Post>(
				postMapper.searchPostList(start, pageSize, title),
				postMapper.searchTotal(title), pageNum, pageSize);
		return pageInfo;
	}

	//收藏夹
	@Override
	public int countByUserId(int userId) {
		return postMapper.countPcollectByUserId(userId);
	}
	@Override
	public int countByPostId(int postId) {
		return postMapper.countPcollectByPostId(postId);
	}
	@Override
	public int countIsPcollectByUserIdAndPostId(int userId, int postId) {
		return postMapper.countIsPcollectByUserIdAndPostId(userId, postId);
	}
	@Override
	public void addCollect(int postId,int userId) {
		postMapper.insertPcollect(postId, userId);
		postMapper.updateAddCollect(postId);
	}
	@Override
	public void delCollect(int postId, int userId) {
		postMapper.delPcollect(postId,userId);
		postMapper.updateDelCollect(postId);
	}

}

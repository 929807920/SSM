package com.sunjinxu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sunjinxu.pojo.Post;

public interface PostMapper {
	List<Map<String, String>> findAllTagByDesc(int limit) throws Exception;
	public List<Object> findAllType();
	public int countTags();
	public int insert(Post post); 
	public int insertTag(Post post);
	public int insertAttributes(Post post);
	public int insertPostTag(Post post);
	public void delete(int id);
	public void deleteAttr(int id);
	public void deletePostTag(int id);
	public void updateTagFrequencyDown(int id);
	public int updateTagFrequencyUp(String tagName);
	public void deleteTag0(int id);
	public int getTagIdByPostId(int id);
	public Post get(int id);
	public Post getFullOne(int id);
	public int update(Post post);
	public void updateView(int postId);
	public List<Post> list(@Param("start") int start,@Param("pageSize") int pageSize);
	public List<Post> searchPostList(@Param("start") int start,@Param("pageSize") int pageSize,@Param("title") String title);
	public int total();
	public int checkTag(String tagName);
	public int getTagId(String tagName);
	public int searchTotal(String title);
	public int countByUserId(int id);
	//浏览记录
	public void insertPview(@Param("postId") int postId,@Param("viewTime") String viewTime,@Param("ip") String ip);
	public void updatePview(@Param("postId") int postId,@Param("viewTime") String viewTime,@Param("ip") String ip);
	public int countPview(@Param("postId") int postId,@Param("ip") String ip);
	public String getPviewTime(@Param("postId") int postId,@Param("ip") String ip);
	//收藏夹
	public void insertPcollect(@Param("postId") int postId,@Param("userId") int userId);
	public int countPcollectByUserId(int userId);
	public int countPcollectByPostId(int postId);
	public void updateAddCollect(int postId);
	public void updateDelCollect(int postId);
	public int countIsPcollectByUserIdAndPostId(@Param("userId") int userId,@Param("postId") int postId);
	public void delPcollect(@Param("postId") int postId,@Param("userId") int userId);
}

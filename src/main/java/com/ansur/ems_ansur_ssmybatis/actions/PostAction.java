package com.ansur.ems_ansur_ssmybatis.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.ansur.ems_ansur_ssmybatis.models.Department;
import com.ansur.ems_ansur_ssmybatis.models.Post;
import com.ansur.ems_ansur_ssmybatis.models.PostMapper;
import com.opensymphony.xwork2.ActionSupport;

public class PostAction {

	@Autowired
	private PostMapper pMapper;

	private Post post;

	private List<Post> postList;
	
	private String message_one;
	private String errorMessage_one;

	public String execute() {
		postList = pMapper.getPostList();
		post = new Post();
		post.setP_number(getAutoPostNumber());
		return ActionSupport.SUCCESS;
	}

	//役職をDBに入れるために
	public String postInsert() {
		
		
		if (getPost().getP_name().equals("")) {
			setErrorMessage_one("赤い「＊」の項目は必要です。");
			postList = pMapper.getPostList();
			return ActionSupport.ERROR;
		}
		if (pMapper.checkAlreadyExist(getPost().getP_name())) {
			setErrorMessage_one("この部門はもう登録しました。");
			postList = pMapper.getPostList();
			return ActionSupport.ERROR;
		}
		pMapper.insertPost(getPost());
		postList = pMapper.getPostList();
		clear();
		return ActionSupport.SUCCESS;
	}
	
	public String postEditPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		post = pMapper.getPostById(Integer.valueOf(request.getParameter("id")));
		return ActionSupport.SUCCESS;
	}
	
	public String postUpdate() {
		if (getPost().getP_name().equals("")) {
			setErrorMessage_one("赤い「＊」の項目は必要です。");
			post = pMapper.getPostById(Integer.valueOf(getPost().getP_id()));
			return ActionSupport.ERROR;
		}
		Post p = new Post();
		try {
			p = pMapper.getPostByName(getPost().getP_name());
			if (!Integer.valueOf(p.getP_id()).equals(post.getP_id())) {
				setErrorMessage_one("この役職はもう登録しました。");
				post = pMapper.getPostById(Integer.valueOf(getPost().getP_id()));
				return ActionSupport.ERROR;
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		pMapper.updatePost(getPost());
		setMessage_one("更新できました。");
		post = pMapper.getPostById(Integer.valueOf(getPost().getP_id()));
		return ActionSupport.SUCCESS;
	}
	
	public String postDelete() {
		HttpServletRequest request= ServletActionContext.getRequest();
		pMapper.deleteById(Integer.valueOf(request.getParameter("id")));
		postList = pMapper.getPostList();
		post = new Post();
		post.setP_number(getAutoPostNumber());
		return ActionSupport.SUCCESS;
	}
	

	//役職番号を作ること
	private String getAutoPostNumber() {
		// TODO Auto-generated method stub
		String nextPostNum = "post01";
		String postNumPrefix = "post";
		String postNumSuffix;
		try {
			int lastPostNum = Integer.valueOf(pMapper.getLastPostNum().split("t")[1]) + 1;
			postNumSuffix = (lastPostNum < 10) ? "0" + String.valueOf(lastPostNum) : String.valueOf(lastPostNum);
			nextPostNum = postNumPrefix.concat(postNumSuffix);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return nextPostNum;
	}

	private void clear() {
		// TODO Auto-generated method stub
		post.setP_number(getAutoPostNumber());
		post.setP_name("");
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public String getMessage_one() {
		return message_one;
	}

	public void setMessage_one(String message_one) {
		this.message_one = message_one;
	}

	public String getErrorMessage_one() {
		return errorMessage_one;
	}

	public void setErrorMessage_one(String errorMessage_one) {
		this.errorMessage_one = errorMessage_one;
	}
	
	

}

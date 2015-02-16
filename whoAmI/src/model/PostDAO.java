package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databean.Post;

public class PostDAO extends GenericDAO<Post> {
	public PostDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(Post.class, tableName, cp);
	}

	public Post getPost(long twitId) throws RollbackException {
		Post[] Posts = match(MatchArg.equals("twitId", twitId));
		if (Posts.length == 0) {
			return null;
		} else {
			return Posts[0];
		}
	}
	
	public Post[] getPostByUser(long userId) throws RollbackException {
		Post[] postList = match(MatchArg.equals("userId", userId));
		return postList;
	}
	
	public Post[] getUnresolvedPosts() throws RollbackException {
		Post[] postList = match(MatchArg.equals("resolved", false));
		return postList;
	}

}

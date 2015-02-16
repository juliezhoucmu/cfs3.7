//Name:Xu Zhao
//Andrew ID:xuzhao
//Course Number:08600
//Date:12/05/2014
package model;


import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databean.Comment;

public class CommentDAO extends GenericDAO<Comment> {
	public CommentDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(Comment.class, tableName, cp);
	}

	public Comment[] getComments() throws RollbackException {
		Comment[] comments = match();
		// We want them sorted by last and first names (as per
		// User.compareTo());
		return comments;
	}
	
	public Comment getComments(String name) throws RollbackException {
		Comment[] comments = match(MatchArg.equals("name", name));
		if (comments.length == 0) {
			return null;
		}
		else {
			return comments[0];
		}

	}
	
	public Comment getComments(int id) throws RollbackException {
		Comment[] comments = match(MatchArg.equals("comment_id", id));
		if (comments.length == 0) {
			return null;
		}
		else {
			return comments[0];
		}

	}
}

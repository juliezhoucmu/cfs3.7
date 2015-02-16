package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import databean.TwitterUser;

public class TwitterUserDAO extends GenericDAO<TwitterUser> {
	public TwitterUserDAO(ConnectionPool cp, String tableName)
			throws DAOException {
		super(TwitterUser.class, tableName, cp);
	}

	public TwitterUser[] getTwitterUsers() throws RollbackException {
		TwitterUser[] twitterUsers = match();
		return twitterUsers;
	}

	
	public void updateScore(long userId, long score) throws RollbackException {

		TwitterUser user = read(userId);
		if (user == null) {
			throw new RollbackException("user " + userId + " no longer exists");
		}
		user.setScore(score);
		System.out.println("latest score" + score);
		update(user);

	}

	public TwitterUser getTwitterUser(long userId) throws RollbackException {
		TwitterUser[] users = match(MatchArg.equals("userId", userId));
		if (users.length == 0) {
			return null;
		} else {
			return users[0];
		}
	}	
}

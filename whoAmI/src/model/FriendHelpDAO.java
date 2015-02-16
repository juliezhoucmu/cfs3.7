package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databean.FriendHelp;

public class FriendHelpDAO extends GenericDAO<FriendHelp> {
	public FriendHelpDAO(ConnectionPool cp, String tableName)
			throws DAOException {
		super(FriendHelp.class, tableName, cp);
	}

	public FriendHelp[] getFriendsHelp(long ownerId) throws RollbackException {
		System.out.println("owner ID = "  + ownerId);
		FriendHelp[] list = match(MatchArg.equals("ownerId", ownerId));
		return list;
	}
}

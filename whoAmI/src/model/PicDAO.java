package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import databean.DateBean;
import databean.Pic;

public class PicDAO extends GenericDAO<Pic> {
	public PicDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(Pic.class, tableName, cp);
	}

	public Pic getPic(String title) throws RollbackException {
		Pic[] pics = match(MatchArg.equals("title", title));
		if (pics.length == 0) {
			return null;
		} else {
			return pics[0];
		}
	}
	public Pic getPicByURL(String url) throws RollbackException {
		Pic[] pics = match(MatchArg.equals("url", url));
		if (pics.length == 0) {
			return null;
		} else {
			return pics[0];
		}
	}
	public Pic getPic(long id) throws RollbackException {
		Pic[] pics = match(MatchArg.equals("picId", id));
		if (pics.length == 0) {
			return null;
		} else {
			return pics[0];
		}
	}
	
	public Pic[] getPics() throws RollbackException {
		Pic[] piclist = match();
		return piclist;
	}

}

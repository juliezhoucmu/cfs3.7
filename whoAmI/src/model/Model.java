
package model;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

public class Model {
	private DateDAO dDAO;
	private CommentDAO commentDAO;
	private TwitterUserDAO twitterUserDAO;
	private PicDAO picDAO;
	private PostDAO postDAO;
	private FriendHelpDAO friendHelpDAO;

	public Model(ServletConfig _config) throws ServletException {
		try {
			String jdbcDriver = _config.getInitParameter("jdbcDriverName");
			String jdbcURL = _config.getInitParameter("jdbcURL");
			ConnectionPool pool = new ConnectionPool(jdbcDriver, jdbcURL);

			commentDAO = new CommentDAO(pool, "Comment");
			twitterUserDAO = new TwitterUserDAO(pool, "twitterUser");
			dDAO = new DateDAO(pool, "Date");
			picDAO = new PicDAO(pool,"pic");
			postDAO = new PostDAO(pool,"post");
			friendHelpDAO = new FriendHelpDAO(pool,"friendhelp");

		} catch (DAOException e) {
			throw new ServletException(e);
		}
	}

	public TwitterUserDAO getTwitterUserDAO() {
		return twitterUserDAO;
	}

	public DateDAO getDateDAO() {
		return dDAO;
	}

	public CommentDAO getCommentDAO() {
		return commentDAO;
	}

	public PicDAO getPicDAO() {
		return picDAO;
	}
	
	public PostDAO getPostDAO() {
		return postDAO;
	}
	
	public FriendHelpDAO getFriendHelpDAO() {
		return this.friendHelpDAO;
	}
}

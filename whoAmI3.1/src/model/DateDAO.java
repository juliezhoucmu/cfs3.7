package model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;

import databean.DateBean;

public class DateDAO extends GenericDAO<DateBean> {
	public DateDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(DateBean.class, tableName, cp);
	}
 
    	public String getDate() throws RollbackException
    	{
    		String d = null;
    		try 
    		{
				DateBean[] date=match();
				d=date[date.length-1].getDate();
			}
    		catch (RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
										}
    		return d;
    		
    		
    		
    	}

}
          



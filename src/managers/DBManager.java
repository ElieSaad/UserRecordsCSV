/**
 * 
 */
package managers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.UserRecord;
import database.DBRegistry;

/**
 * @author ES
 *
 */
public abstract class DBManager {
    private boolean connectionFree = true;
    
    protected abstract Connection getConnection ();
    protected abstract void setConnection(Connection connection);
    protected abstract void releaseConnection ();
    protected abstract UserRecord getOneRecord (int id);
    protected abstract List<?> getRecords (int id);
    protected abstract boolean addRecord (int id, Object record);
    
	/**
	 * @return the connectionFree
	 */
	public boolean isConnectionFree() {
		return connectionFree;
	}
	/**
	 * @param connectionFree the connectionFree to set
	 */
	public void setConnectionFree(boolean connectionFree) {
		this.connectionFree = connectionFree;
	}
	
    public void close ()
    {
        try
        {
        	this.getConnection();
            DBRegistry.getUniqueInstance().closeDbConnection();
        }
        catch (SQLException e)
        {
            System.out.println (e.getMessage ());
        }
    }
}

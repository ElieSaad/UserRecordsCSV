/**
 * 
 */
package managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dao.UserRecord;
import database.DBRegistry;

/**
 * @author ES
 *
 */
public class RecordsDBManager extends DBManager {
	private final String tableName = "BIOMUSIC.RECORDS";
	private Connection connection;
	
	public RecordsDBManager () throws Exception {
		try
        {
        	DBRegistry.getUniqueInstance().initDBConnection();
            this.setConnection(DBRegistry.getUniqueInstance().getDBConnection());
        }
        catch (Exception e)
        {
            throw new Exception ("Couldn't open connection to Public database: " + e.getMessage ());
        }
	}
	@Override
	protected synchronized Connection getConnection() {
        
		while (!this.isConnectionFree())
        {
            try
            {
                wait ();
            }
            catch (InterruptedException e)
            {
            }
        }
        this.setConnectionFree(false);;
        notify ();
        return connection;
	}
	
	@Override
	protected void setConnection(Connection connection) {
        
		this.connection = connection;
	}

	@Override
	protected synchronized void releaseConnection() {
		
		while (this.isConnectionFree())
        {
            try
            {
                wait ();
            }
            catch (InterruptedException e)
            {
            }
        }
        this.setConnectionFree(true);
        notify ();
	}

	@Override
	protected UserRecord getOneRecord(int id) {
    	
		UserRecord userRecord = null;
        try
        {
            this.getConnection ();
            PreparedStatement preparedStatement = this.connection.prepareStatement ("SELECT id, sex, age, skin_temperature, conductance, bvp FROM " + tableName + " WHERE id = ?");
            preparedStatement.setInt (1, id);
            //ResultSet resultSet = preparedStatement.executeQuery ();
//            if (resultSet.next ())
//            {
//                recordDAO = new RecordDAO
//                (
//                    resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getLong (4),
//                    resultSet.getLong (5), resultSet.getLong (6)
//                );
//            }
            preparedStatement.close ();
            this.releaseConnection ();
            return userRecord;
        }
        catch (SQLException e)
        {
            this.releaseConnection ();
            e.printStackTrace();
            return null;
        }
	}

	@Override
	protected List<?> getRecords(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean addRecord(int id, Object record) {
		// TODO Auto-generated method stub
		return false;
	}

}

/**
 * 
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ES
 *
 */
public class DBRegistry {
	private static DBRegistry soleInstance;
	private static String dbURL = "jdbc:derby:/Users/ES/Documents/workspace/MSAM/database;create=true;user=msam;password=Dayrna";
    private static String dbDriver = "org.apache.derby.jdbc.EmbeddedDriver";
    
    // jdbc Connection
    private static Connection dbConnection = null;
    private static Statement stmt = null;
	
	private DBRegistry() {}
	
	public static DBRegistry getUniqueInstance() {
		if(soleInstance == null)
    		soleInstance = new DBRegistry();
        return soleInstance;
	}
	
	public void initDBConnection()
    {
        try {
            Class.forName(dbDriver).newInstance();
            //Get a connection
            dbConnection = DriverManager.getConnection(dbURL); 
        } 
        catch (Exception except) {
            except.printStackTrace();
        }
    }
	
	public boolean isConnected() {
		return dbConnection != null;
	}
	
	public Connection getDBConnection() throws SQLException {
		if (!isConnected()) {
			initDBConnection();
		}
		return dbConnection;
	}
	
	public void closeDbConnection() throws SQLException  {
		try
		{
			if (stmt != null) {
				stmt.close();
			}
			if (dbConnection != null) {
				DriverManager.getConnection(dbURL + ";shutdown=true");
				dbConnection.close();
				dbConnection = null;
			}
		} 
		catch (SQLException sqlExcept) {
			
		}
	}
}

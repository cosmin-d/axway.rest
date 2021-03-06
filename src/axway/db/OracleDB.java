package axway.db;

import java.sql.Connection;

import javax.naming.*;
import javax.sql.*;

public class OracleDB {

	private static DataSource oracle_ds = null;
	private static Context context = null;
	
	public static DataSource OracleDS() throws Exception {
		
		if(oracle_ds != null)//oracle_ds is static --> trying to optimize 
			return oracle_ds;
		
		try {
			
			if(context == null)
				context = new InitialContext();
			
			}
		
		
		catch (Exception e) {
		
			e.printStackTrace();
		}
		
		oracle_ds = (DataSource) context.lookup("axwaydb");//database is referenced as 'axwaydb'
		return oracle_ds;
	}
	
	
	protected static Connection databaseConnection() {  //--> trying to optimize 
		Connection conn = null;
		try {
			conn = OracleDS().getConnection();
			return conn;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}

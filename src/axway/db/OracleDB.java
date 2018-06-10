package axway.db;

import javax.naming.*;
import javax.sql.*;

public class OracleDB {

	private static DataSource oracle_ds = null;
	private static Context context = null;
	
	public static DataSource OracleDS() throws Exception {
		
		if(oracle_ds != null)
			return oracle_ds;
		
		try {
			
			if(context == null)
				context = new InitialContext();
			
			}
		
		
		catch (Exception e) {
		
			e.printStackTrace();
		}
		
		oracle_ds = (DataSource) context.lookup("axwaydb");
		return oracle_ds;
	}
	
	
}

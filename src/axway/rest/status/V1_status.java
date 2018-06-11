package axway.rest.status;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import axway.db.*;

import java.sql.*;

@Path("/v1/status/")

public class V1_status {

	private static String api_version ="1.0.0";
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Service</p>";
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String 	returnVersion() {
		return "<h2>API version: "+ api_version +"</h2>";
	}

	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception {
		
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		
		try {
			conn = OracleDB.OracleDS().getConnection();
			query = conn.prepareStatement("select to_char (sysdate, 'YYYY-MM-DD HH24:MI:SS') DATETIME from sys.dual");
			ResultSet rs = query.executeQuery();
			while(rs.next()) {
				myString = rs.getString("DATETIME");
			}
			
			query.close();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(conn != null)
				conn.close();//close connection
			}
		returnString = "<p>Database status</p>" + 
		"<p>Database Date/Time return: " + myString + "</p>";
		
		return returnString;
		
	}

}

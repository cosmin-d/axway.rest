package axway.rest.crud;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.*;

import axway.db.*;
import axway.rest.util.*;

import org.codehaus.jettison.json.JSONArray;


@Path("/v1/read/courses")  //api path
public class V1_readCourses {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)//returns JSON
	public Response returnCourses() throws Exception{
		
		PreparedStatement query = null;
		Connection conn = null;
		String returnString = null;
		Response rb = null;


try {
	conn = OracleDB.OracleDS().getConnection();
	query =conn.prepareStatement("select * from CURS");//using * in sql queries is not recommended for production code 
	
	ResultSet rs = query.executeQuery();
	
	ToJSON converter = new ToJSON();
	JSONArray json = new JSONArray();
	json = converter.toJSONArray(rs);
	query.close();
	
	returnString = json.toString();
	rb = Response.ok(returnString).build();
}

catch(Exception e) {
	e.printStackTrace();
}
finally {              		//connection must be closed every time
	if(conn != null)
		conn.close();
}

return rb;
	}
}

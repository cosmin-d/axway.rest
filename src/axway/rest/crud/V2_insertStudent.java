package axway.rest.crud;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import axway.db.*;


@Path("/v2/insert/student")
public class V2_insertStudent {

	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response insertStudent2(String incomingData) throws Exception {
		
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		Functions fun = new Functions();
		
		try {
				
			JSONObject studentData = new JSONObject(incomingData);
			
			System.out.println( "jsonData: " + studentData.toString() );
			
			
			
			int http_code = fun.insertStudent(studentData.optString("NUME"), 
												studentData.optString("PRENUME"));

			if( http_code == 200 ) { //making use of the HTTP protocol
			
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been entered successfully");

				returnString = jsonArray.put(jsonObject).toString();
				
			} else {
				return Response.status(500).entity("Unable to enter Item").build();
			}
			
			System.out.println( "returnString: " + returnString );
			
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
}
	

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


@Path("/v2/insert/stc")
public class V2_insertStudent_to_Course {

		@POST
		@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
		@Produces(MediaType.APPLICATION_JSON)
		
		public Response insertStudent_to_Course2(String incomingData) throws Exception {
			
			String returnString = null;
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			Functions fun = new Functions();
			
			try {
					
				JSONObject data = new JSONObject(incomingData);
				
				System.out.println( "jsonData: " + data.toString() );
				
				
				
				int http_code = fun.insertStudent_to_Course(data.optString("ID_STUDENT"), 
													data.optString("ID_CURS"));

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
		



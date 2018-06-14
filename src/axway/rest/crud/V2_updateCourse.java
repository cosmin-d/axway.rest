package axway.rest.crud;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import axway.db.*;

@Path("/v2/update/course/{id}/")
public class V2_updateCourse {

	@PUT
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateItem(@PathParam("id") int id,
									String incomingData) 
								throws Exception {
		
		
		String denumire;
		int http_code;
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		Functions fun = new Functions();
		
		try {
			
			JSONObject courseData = new JSONObject(incomingData); //using json objects to parse data
			denumire= courseData.optString("DENUMIRE");
			
			http_code = fun.updateCourse(id, denumire);
			
			if(http_code == 200) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been updated successfully");
			} else {
				return Response.status(500).entity("Server was not able to process your request").build();
			}
			
			returnString = jsonArray.put(jsonObject).toString();
			
		} catch(Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
}
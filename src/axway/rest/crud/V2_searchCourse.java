package axway.rest.crud;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import axway.db.Functions;

@Path("/v2/search/course")
public class V2_searchCourse {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnError(
				@QueryParam("denumire") String denumire)
				throws Exception {
		
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try {
			
			//return a error is brand is missing from the url string
			if(denumire == null) {
				return Response.status(400).entity("Error: please enter /denumire").build();
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
	
	@Path("/{denumire}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnCourseParticipants(
				@PathParam("denumire") String denumire)
				throws Exception {
		
		String returnString = null;
		
		JSONArray json = new JSONArray();
		
		try {
			
			Functions dao = new Functions();
			
			json = dao.queryByCourse(denumire);
			returnString = json.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
	
	
	
	
}

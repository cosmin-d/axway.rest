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

@Path("/v2/search/student")
public class V2_searchStudent {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrandParts(
				@QueryParam("nume") String nume)
				throws Exception {
		
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try {
			
			//return a error is brand is missing from the url string
			if(nume == null) {
				return Response.status(400).entity("Error: please enter /nume/prenume").build();
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
	
	
	@Path("/{nume}/{prenume}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnCoursesForStudent(
				@PathParam("nume") String nume,
				@PathParam("prenume") String prenume) 
				throws Exception {
		
		String returnString = null;
		
		JSONArray json = new JSONArray();
		
		if(nume == null || prenume ==null) {
			try {
				
				//return an error if  parameters are missing from url string
					return Response.status(400).entity("Error: please enter /nume/prenume").build();
				}
				
			catch (Exception e) {
				e.printStackTrace();
				return Response.status(500).entity("Server was not able to process your request").build();
			}
			
		}
		
		try {
			
			Functions dao = new Functions();
			
			json = dao.queryByStudent(nume, prenume);
			returnString = json.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
	
	
	
}

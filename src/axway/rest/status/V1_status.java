package axway.rest.status;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/v1/status/")

public class V1_status {

	private static String api_version ="1.0.0";
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String 	returnTitle() {
		return "<p>Java Web Service</p>";
	}
	
@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String 	returnVersion() {
		return "<h2>API version: "+ api_version +"</h2>";
	}
}

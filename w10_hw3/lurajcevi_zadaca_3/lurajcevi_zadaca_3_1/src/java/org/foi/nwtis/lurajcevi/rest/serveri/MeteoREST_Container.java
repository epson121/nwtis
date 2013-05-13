
package org.foi.nwtis.lurajcevi.rest.serveri;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author nwtis_2
 */
@Path("/meteoREST")
public class MeteoREST_Container {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MeteoREST_Container
     */
    public MeteoREST_Container() {
    }

    /**
     * Retrieves representation of an instance of org.foi.nwtis.lurajcevi.rest.serveri.MeteoREST_Container
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getHtml() {
        return "OK";
    }

    /**
     * POST method for creating an instance of MeteoREST
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes("text/html")
    @Produces("text/html")
    public Response postHtml(String content) {
        //TODO
        return Response.created(context.getAbsolutePath()).build();
    }

    /**
     * Sub-resource locator method for {zip}
     */
    @Path("{zip}")
    public MeteoREST getMeteoREST(@PathParam("zip") String zip) {
        return MeteoREST.getInstance(zip);
    }
    
    
}

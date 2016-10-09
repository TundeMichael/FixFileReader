package com.tundemichael.reader;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * REST Web Service
 *
 * @author Michael.Orokola
 */
@Path("subscribers")
public class SubscribersResource {

    @Context
    private UriInfo context;
    private static final Logger LOG = Logger.getLogger(SubscribersResource.class.getName());

    /**
     * Retrieves representation of an instance of
     * com.tundemicahel.seamfix.SubscribersResource
     *
     * @param phone
     * @return an instance of javax.ws.rs.core.Response
     */
    @GET
    @Path("{phone}")
    @Produces("application/xml")
    public Response getSubscriber(@PathParam("phone") String phone) {
        try {
            File file = new File("C:/Users/TundeMichael/Documents/Reader/"
                    + phone.trim() + ".xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Subscriber.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Subscriber subscriber = (Subscriber) jaxbUnmarshaller.unmarshal(file);
            System.out.println(subscriber);
            return Response.ok(subscriber).build();
        } catch (JAXBException e) {
            LOG.log(Level.SEVERE, null, e);
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}

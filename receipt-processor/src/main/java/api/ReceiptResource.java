package api;

import driver.ReceiptProcessorDriver;
import io.dropwizard.core.Application;
import io.dropwizard.core.Configuration;
import io.dropwizard.core.setup.Environment;
import jakarta.validation.Valid;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Receipt;

import java.util.Map;


@Path("/receipts")
@Produces(MediaType.APPLICATION_JSON)
public class ReceiptResource extends Application<Configuration> {
    ReceiptProcessorDriver receiptProcessorDriver;

    public ReceiptResource() {
         receiptProcessorDriver = new ReceiptProcessorDriver();
    }
    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        environment.jersey().register(this);
    }
    @POST
    @Path("/process")
    public Response process(@Valid Receipt receipt) {
        String id = receiptProcessorDriver.generateId(receipt);
        return Response.ok(Map.of("id", id)).build();
    }
    @GET
    @Path("{id}/points")
    public Response points(@PathParam("id") String id) {
        Receipt receipt = receiptProcessorDriver.getReceiptById(id);

        if (receipt == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No receipt found for that ID.")
                    .build();
        }
        return Response.ok(Map.of("points:",receiptProcessorDriver.getPointsById(id))).build();
    }
}


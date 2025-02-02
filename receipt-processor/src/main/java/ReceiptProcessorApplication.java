import api.ReceiptResource;
import errorhandler.CustomConstraintViolationExceptionMapper;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;


public class ReceiptProcessorApplication  extends Application<ApplicationConfig> {

    public static void main(String[] args) throws Exception {
        new ReceiptProcessorApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<ApplicationConfig> bootstrap) {
    }

    @Override
    public void run(ApplicationConfig configuration, Environment environment) {
        environment.jersey().register(CustomConstraintViolationExceptionMapper.class);
        environment.jersey().register(new ReceiptResource());
    }
}
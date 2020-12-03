package dev.stratospheric.cdk;

import software.amazon.awscdk.core.Construct;
import software.amazon.awscdk.core.Environment;
import software.amazon.awscdk.core.Stack;
import software.amazon.awscdk.core.StackProps;

import java.util.Collections;

/**
 * This stack creates a {@link Network} and a {@link Service} that deploys a given Docker image. The {@link Service} is
 * pre-configured for a Spring Boot application.
 *
 * This construct is for demonstration purposes since it's not configurable and thus of little practical use.
 */
public class SpringBootApplication extends Stack {

    public SpringBootApplication(
            final Construct scope,
            final String id,
            final Environment environment,
            final String dockerImageUrl) {
        super(scope, id, StackProps.builder()
                .stackName("SpringBootApplication")
                .env(environment)
                .build());


        Network network = new Network(this, "network", environment, "prod", new Network.NetworkInputParameters());
        Service service = new Service(this, "Service", environment, new ApplicationEnvironment("SpringBootApplication", "prod"),
                new Service.ServiceInputParameters(
                        new Service.DockerImageSource(dockerImageUrl),
                        Collections.emptyList(),
                        Collections.emptyMap()),
                network.getOutputParameters());

    }

}

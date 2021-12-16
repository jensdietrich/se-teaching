package nz.ac.vuw.jenz.rest.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Utilities.
 * @author jens dietrich
 */
abstract class Helpers {

    static ObjectMapper mapper = new ObjectMapper();
    static ClientConfig clientConfig = new DefaultClientConfig();
    static Client client = Client.create(clientConfig);
}

package test.nz.ac.vuw.jenz.misc;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import java.net.URI;
import static org.junit.Assert.assertTrue;

/**
 * Test whether google knows the answer to life the universe and everything.
 * If it fails, check console for timeouts and proxy issues (this does not work behind proxies requiring authentication).
 * @author jens dietrich
 */
public class TestWhetherGoogleKnowsTheAnswer {

    @Test
    public void test() throws Exception {
        // using a builder is much easier than manually encoding the URL with escaping white spaces in the query etc
        // would be something like:
        // https://www.google.com/search?q=the+answer+to+life+the+universe+and+everything&oq=the+answer+to+life+the+universe+and+everything
        URI uri = new URIBuilder()
            .setScheme("http")
            .setHost("www.google.com")
            .setPath("/search")
            .setParameter("q", "the answer to life the universe and everything")
            .build();

        // create and execute the request
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(uri);
        HttpResponse response = httpClient.execute(request);

        // this string is the unparsed web page (=html source code)
        String content = EntityUtils.toString(response.getEntity());

        // check whether the web page contains the expected answer
        assertTrue(content.indexOf("42") > -1);
    }

}

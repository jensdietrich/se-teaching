package nz.ac.vuw.jenz.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testWelcome() {
        String s = restTemplate.getForObject("http://localhost:" + port + "/welcome", String.class);
        System.out.println("port is: " + port);
        assertThat(s).isEqualTo("Welcome to Vic Kritters!");
    }

    @Test
    void testFindOrder1() throws Exception {
        Order order = restTemplate.getForObject("http://localhost:" + port + "/orders/1", Order.class);
        assertThat(order).isNotNull();
        Order expectedOrder = PetstoreDB.getOrder(1);  // oracle
        assertThat(order).isEqualTo(expectedOrder);
    }

    @Test
    void testFindOrder2() throws Exception {
        Order order = restTemplate.getForObject("http://localhost:" + port + "/orders/2", Order.class);
        assertThat(order).isNotNull();
        Order expectedOrder = PetstoreDB.getOrder(2);  // oracle
        assertThat(order).isEqualTo(expectedOrder);
    }



}

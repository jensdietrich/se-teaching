package nz.ac.vuw.jenz.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestClientException;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    // always reset datastore to avoid test order dependencies
    @BeforeEach
    public void setup() {
        PetstoreDB.reset();
    }

    @Test
    void testWelcome() {
        String s = restTemplate.getForObject("http://localhost:" + port + "/welcome", String.class);
        System.out.println("port is: " + port);
        assertThat(s).isEqualTo("Welcome to Vic Kritters!");
    }

    @Test
    void testFindOrderById1() {
        Order order = restTemplate.getForObject("http://localhost:" + port + "/orders/1", Order.class);
        assertThat(order).isNotNull();
        Order expectedOrder = PetstoreDB.get(1);  // oracle
        assertThat(order).isEqualTo(expectedOrder);
    }

    @Test
    void testFindOrderById2() {
        Order order = restTemplate.getForObject("http://localhost:" + port + "/orders/2", Order.class);
        assertThat(order).isNotNull();
        Order expectedOrder = PetstoreDB.get(2);  // oracle
        assertThat(order).isEqualTo(expectedOrder);
    }

    // the API used here is very high level as we don't get direct access to the actual status code,
    // this is only visible throw the exception message
    @Test
    void testFindOrderByIDFails() throws Exception {
        Exception exception = assertThrows(
            RestClientException.class,
            () -> restTemplate.getForEntity("http://localhost:" + port + "/orders/42", Order.class)
        );
        assertThat(exception.getMessage().contains("404"));
    }

    @Test
    void testSearchByItemName()  {
        List<Order> orders = restTemplate.getForObject("http://localhost:" + port + "/search?keyword=angel", List.class);
        assertThat(orders).isNotNull();
        assertThat(orders.size()).isEqualTo(2);
        assertThat(orders.contains(PetstoreDB.get(2)));
        assertThat(orders.contains(PetstoreDB.get(7)));
    }

    @Test
    void testPostNewOrder() throws Exception {
       // first check that the object is not already in the DB
       Order order =  PetstoreDB.get(42);
       assumeTrue(order==null);  // assumption instead of assertion - if this fails test is meaningless

       Order newOrder = new Order(42,OrderStatus.NEW,"Ivanacara bimaculata",69.00, 6,"jdietrich");
       restTemplate.postForObject("http://localhost:" + port + "/orders", newOrder, Order.class);

       // retrieve and check
       order =  PetstoreDB.get(42);
       assertThat(order).isNotNull();
       assertThat(order).isEqualTo(newOrder);
       assertThat(order).isNotSameAs(newOrder);  // since newOrder goes through derserialise/serialise, it is not the same
    }

    @Test
    void testDeleteOrder() throws Exception {
        // first check that the object is in the DB
        Order order =  PetstoreDB.get(1);
        assumeTrue(order!=null);  // assumption instead of assertion - if this fails test is meaningless

        restTemplate.delete("http://localhost:" + port + "/orders/1");

        // retrieve and check
        order =  PetstoreDB.get(1);
        assertThat(order).isNull();
    }

}

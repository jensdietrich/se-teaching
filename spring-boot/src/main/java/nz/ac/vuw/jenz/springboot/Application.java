package nz.ac.vuw.jenz.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to Vic Kritters!";
	}

	// provides an object, this is to be mapped to JSON
	// @RequestParam(value = "id") would pick up the parameter from the URL query part
	// as in http://localhost:8080/orders?id=1
	// here we use a path parameter instead
	// as in http://localhost:8080/orders/1
	@GetMapping("/orders/{id}")
	public Order getOrderById(@PathVariable(value = "id") int id) throws ResponseStatusException {
		Order order = PetstoreDB.getOrder(id);
		if (order == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No order found with id " + id);
		}
		else {
			return order;
		}
	}

	@GetMapping("/search")
	public List<Order> getOrderByItemName(@RequestParam(value = "keyword") String keyword) {
		return PetstoreDB.searchOrderByItemName(keyword);
	}

}

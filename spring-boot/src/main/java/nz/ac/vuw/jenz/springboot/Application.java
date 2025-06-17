package nz.ac.vuw.jenz.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
		Order order = PetstoreDB.get(id);
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

	@PostMapping("/orders")
	void add(@RequestBody Order newOrder) {
		PetstoreDB.add(newOrder);
	}

	@DeleteMapping("/orders/{id}")
	boolean delete(@PathVariable(value = "id")  int id) {
		Order order = PetstoreDB.get(id);
		if (order == null) {
			return false;
		}
		else {
			return PetstoreDB.delete(order);
		}
	}

}

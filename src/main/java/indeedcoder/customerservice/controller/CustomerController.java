package indeedcoder.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import indeedcoder.customerservice.consumer.LoadBalancerClientCartRestConsumer;

@RestController
public class CustomerController {

	private LoadBalancerClientCartRestConsumer cartRestConsumer;

	private static String msg = "Response from cart service requested By Customer service:";

	@Autowired
	public CustomerController(LoadBalancerClientCartRestConsumer cartRestConsumer) {
		this.cartRestConsumer = cartRestConsumer;
	}

	@GetMapping("")
	public ResponseEntity<String> getHome(){
		return ResponseEntity.ok("This is customer service.");
	}

	@GetMapping("/cart")
	public ResponseEntity<String> getCartHome(){
		ResponseEntity<String> cartResponse = cartRestConsumer.getCartHome();
		return ResponseEntity.ok(msg + cartResponse.getBody());
	}

	@GetMapping("/cart/port")
	public ResponseEntity<String> getCartPort(){
		ResponseEntity<String> cartResponse = cartRestConsumer.getCartPort();
		return ResponseEntity.ok(msg + cartResponse.getBody());
	}

	@GetMapping("/health")
	public ResponseEntity<String> getHealth(){
		return ResponseEntity.ok("customer service is in good health.");
	}

	@GetMapping("/cart-health")
	public ResponseEntity<String> getCartHealth(){
		ResponseEntity<String> cartResponse = cartRestConsumer.getCartHealth();
		return ResponseEntity.ok(msg + cartResponse.getBody());
	}

	@GetMapping("/actuator/info")
	public ResponseEntity<String> getActuatorInfo(){
		return ResponseEntity.ok("customer service actuator info.");
	}

	@GetMapping("/cart-actuator/info")
	public ResponseEntity<String> getCartActuatorInfo(){
		ResponseEntity<String> cartResponse = cartRestConsumer.getCartActuatorInfo();
		return ResponseEntity.ok(msg + cartResponse.getBody());
	}
}


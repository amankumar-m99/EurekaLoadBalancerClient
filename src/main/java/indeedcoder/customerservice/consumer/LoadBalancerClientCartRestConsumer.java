package indeedcoder.customerservice.consumer;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LoadBalancerClientCartRestConsumer {

	private LoadBalancerClient loadBalancerClient;

	private RestTemplate restTemplate;

	@Autowired
	public LoadBalancerClientCartRestConsumer(LoadBalancerClient loadBalancerClient) {
		this.loadBalancerClient = loadBalancerClient;
		// Implementation class:
		// org.springframework.cloud.loadbalancer.blocking.client.BlockingLoadBalancerClient
		System.out.println("LoadBalancerClient impl class:" + loadBalancerClient.getClass().getName());
		restTemplate = new RestTemplate();
	}

	public ResponseEntity<String> getCartHome() {
		String url = getCartServiceURI().toString();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		return ResponseEntity.ok(response.getBody());
	}

	public ResponseEntity<String> getCartPort() {
		String url = getCartServiceURI().toString() + "/port";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		return ResponseEntity.ok(response.getBody());
	}

	public ResponseEntity<String> getCartHealth() {
		String url = getCartServiceURI() + "/health";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		return ResponseEntity.ok(response.getBody());
	}

	public ResponseEntity<String> getCartActuatorInfo() {
		String url = getCartServiceURI() + "/actuator/info";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		return ResponseEntity.ok(response.getBody());
	}

	private URI getCartServiceURI() {
		return getCartServiceInstance().getUri();
	}

	private ServiceInstance getCartServiceInstance() {
		return loadBalancerClient.choose("Cart-Service");
	}
}

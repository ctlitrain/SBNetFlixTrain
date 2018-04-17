package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@RestController
//@EnableDiscoveryClient
@SpringBootApplication
public class SayHelloApplication {

  private static Logger log = LoggerFactory.getLogger(SayHelloApplication.class);

  @Value("${server.port}") 
  String port;
  
  @RequestMapping(value = "/greeting")
  public String greet() {
    log.info("Access /greeting");

    List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");
    Random rand = new Random();

    int randomNum = rand.nextInt(greetings.size());
    return (greetings.get(randomNum)+"--at port:"+port);
  }

  @RequestMapping(value = "/")
  public String home() {
    log.info("Access /");
    return "Hi!";
  }

  public static void main(String[] args) {
    SpringApplication.run(SayHelloApplication.class, args);
  }
}

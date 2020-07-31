package net.ccfish.upd4j.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class BusinessApplication  {
    
//    private ConfigurableApplicationContext ctx;

    public static void main(String[] args) throws Exception {
        System.out.println("BusinessApplication started");
        SpringApplication.run(BusinessApplication.class, args);
    }

    @GetMapping("")
    public String helloworld() {
        return "helloworld v17";
    }

//    @Override
//    public void run(LaunchContext context) {
//        System.out.println("BusinessApplication started");
//        ctx = SpringApplication.run(BusinessApplication.class);
//    }
    
}

package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by magic_000 on 21/02/2017.
 */
@RestController
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/")
    String home(){
        return "index";
    }

    @RequestMapping("/phu")
    String run(){

        return "Vũ Hồng Phú";
    }

    public static void main(String... args) {
        SpringApplication.run(SampleController.class, args);
    }
}

package hello;

import com.google.gson.Gson;
import common.msg.CommonMessage;
import entities.Student;
import h.utils.HUtils;
import hiber.Hiberfunction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by magic_000 on 21/02/2017.
 */
@RestController
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/home")
    String home(){
        System.out.println("da chay index.html");
        return "index.html";
    }

    @RequestMapping("/phu")
    String run(){
        return "Vũ Hồng Phú";
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    String findStudentById(@RequestParam("id") String id){
        Integer sid= Integer.parseInt(id);
        Student result= Hiberfunction.getStudentById(sid);
        if(result==null){
            String msgResult;
            CommonMessage cmsg= new CommonMessage("Student not found");
            msgResult= new Gson().toJson(cmsg);
            return msgResult;
        }else{
            String msgResult= new Gson().toJson(result);
            return msgResult;
        }
    }

    public static void main(String... args) {
        HUtils.getSessionFactory();
        SpringApplication.run(SampleController.class, args);
    }
}

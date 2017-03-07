package hello;

import com.google.gson.Gson;
import common.msg.CommonMessage;
import define.err.ErrDefine;
import entities.Student;
import h.utils.HUtils;
import hiber.HiberFunction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by magic_000 on 21/02/2017.
 */
@RestController
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/home")
    String home() {
        System.out.println("da chay index__.html");
        return "index__.html";
    }

    @RequestMapping("/phu")
    String run() {
        return "Vũ Hồng Phú";
    }


    @RequestMapping(value = "/students/all", method = RequestMethod.GET)
    public String getAllStudent(){
        List<Student> allStudentList= HiberFunction.getAllStudent();
        Student[] allStudentArr= new Student[allStudentList.size()];
        allStudentList.toArray(allStudentArr);
        return new Gson().toJson(allStudentArr);
    }


    @RequestMapping(value = "/student", method = RequestMethod.POST)
    String findStudentById(@RequestParam("id") String id) {
        Integer sid = -1;
        try {
            sid = Integer.parseInt(id);
        } catch (NumberFormatException n) {
            CommonMessage cmsg = new CommonMessage(ErrDefine.ID_NOT_VALID);
            return new Gson().toJson(cmsg);
        }
        Student result = HiberFunction.getStudentById(sid);
        if (result == null) {
            String msgResult;
            CommonMessage cmsg = new CommonMessage(ErrDefine.STUDENT_NOT_FOUND);
            msgResult = new Gson().toJson(cmsg);
            return msgResult;
        } else {
            String msgResult = new Gson().toJson(result);
            return msgResult;
        }
    }

    @RequestMapping(value = "/post/json", method = RequestMethod.POST, consumes = "application/json")
    public String postJson(@RequestBody String jsonData){
         return jsonData;
    }

    @RequestMapping(value = "student/find", method = RequestMethod.POST)
    public String searchStudentByName(@RequestParam("name") String name) {
        List<Student> res = HiberFunction.searchByName(name);
        if (res.size() == 0) {
            return new Gson().toJson(new CommonMessage(ErrDefine.STUDENT_NOT_FOUND));
        } else {
            Student[] result = new Student[res.size()];
            res.toArray(result);
            return new Gson().toJson(result);
        }

    }

    //handle add or update student here
    @RequestMapping(value = "/student/add", method = RequestMethod.POST, consumes = "application/json")
    public String addStudent(@RequestBody String studentToAdd) {
        System.out.println(studentToAdd);
        try {
            JSONObject studentJson= new JSONObject(studentToAdd);
            String name= studentJson.getString("name");
            Student studentToBeAdd= new Student();

            studentToBeAdd.setName(name);
            if(studentJson.has("address")){
                studentToBeAdd.setAddress(studentJson.getString("address"));
            }

            if(studentJson.has("age")){
                studentToBeAdd.setAge(studentJson.getInt("age"));
            }
            if(studentJson.has("class")){
                studentToBeAdd.setStudent_class(studentJson.getString("class"));
            }

            if(studentJson.has("gender")){
                studentToBeAdd.setGender(studentJson.getString("gender"));
            }

            if(studentJson.has("email")){
                studentToBeAdd.setEmail(studentJson.getString("email"));
            }

            if(studentJson.has("phone")){
                studentToBeAdd.setPhoneNumber(studentJson.getString("phone"));
            }

            if(studentJson.has("birthday")){
                studentToBeAdd.setBirthday(studentJson.getString("birthday"));
            }
            boolean res=HiberFunction.updateOrAddStudent(studentToBeAdd);
            if(res==false){
                return new Gson().toJson(new CommonMessage(ErrDefine.SERVER_ERROR));
            }
        }catch (JSONException je){
            return new Gson().toJson(new CommonMessage(ErrDefine.DATA_NOT_VALID));
        }
        return new Gson().toJson(new CommonMessage(ErrDefine.SUCCESS));
    }

    @RequestMapping(value = "/student/update", method = RequestMethod.POST, consumes = "application/json")
    public String updateStudent(@RequestBody String studentJson){
        System.out.println(studentJson);
        try {
            JSONObject stdJsonObject= new JSONObject(studentJson);
            if(!stdJsonObject.has("id")){
                return new Gson().toJson(new CommonMessage(ErrDefine.DATA_NOT_VALID));
            }
            int id= stdJsonObject.getInt("id");
            Student studentToUpdate= HiberFunction.getStudentById(id);
            if(studentToUpdate==null){
                return new Gson().toJson(new CommonMessage(ErrDefine.STUDENT_NOT_FOUND));
            }else {
                if(stdJsonObject.has("name")){
                    String stdNewName= stdJsonObject.getString("name");
                    studentToUpdate.setName(stdNewName);
                }
                if(stdJsonObject.has("address")){
                    String stdNewAdd= stdJsonObject.getString("address");
                    studentToUpdate.setAddress(stdNewAdd);
                }

                if(stdJsonObject.has("age")){
                    int stdNewAdd= stdJsonObject.getInt("age");
                    studentToUpdate.setAge(stdNewAdd);
                }

                if(stdJsonObject.has("class")){
                    String newClass= stdJsonObject.getString("class");
                    studentToUpdate.setStudent_class(newClass);
                }

                if(stdJsonObject.has("email")){
                    studentToUpdate.setEmail(stdJsonObject.getString("email"));
                }

                if(stdJsonObject.has("gender")){
                    studentToUpdate.setGender(stdJsonObject.getString("gender"));
                }

                if(stdJsonObject.has("phone")){
                    studentToUpdate.setPhoneNumber(stdJsonObject.getString("phone"));
                }

                if(stdJsonObject.has("birthday")){
                    studentToUpdate.setBirthday(stdJsonObject.getString("birthday"));
                }
                boolean updateSuccess= HiberFunction.updateOrAddStudent(studentToUpdate);
                if(updateSuccess){
                    return new Gson().toJson(new CommonMessage(ErrDefine.SUCCESS));
                }else{
                    return new Gson().toJson(new CommonMessage(ErrDefine.SERVER_ERROR));
                }
            }
        }catch (JSONException je){
            return new Gson().toJson(new CommonMessage(ErrDefine.DATA_NOT_VALID));
        }
        //return null;
    }

    @RequestMapping(value = "student/delete")
    public String deleteStudent(@RequestParam(name = "id") String id){
        try {
            int idStd= Integer.parseInt(id);
            boolean res= HiberFunction.deleteAStudent(idStd);
            if(res==true){
                return new Gson().toJson(new CommonMessage(ErrDefine.SUCCESS));
            }else{
                return new Gson().toJson(new CommonMessage(ErrDefine.SERVER_ERROR));
            }
        }catch (NumberFormatException ne){
            return new Gson().toJson(new CommonMessage(ErrDefine.ID_NOT_VALID));
        }
    }

    public static void main(String... args) {
        //HUtils.getSessionFactory();
        SessionFactory sf= HUtils.getSessionFactory();
        Session ss= sf.getCurrentSession();
        ss.getTransaction().begin();
        String updateAIProps= "ALTER TABLE `student_management_system`.`students` CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT ''";
        NativeQuery nq= ss.createNativeQuery(updateAIProps);
        nq.executeUpdate();
        ss.close();
        SpringApplication.run(SampleController.class, args);
    }
}

package entities;

import javax.persistence.*;

/**
 * Created by magic_000 on 25/02/2017.
 */

@Entity
@Table(name = "students", uniqueConstraints = {@UniqueConstraint(columnNames = "id")},
        indexes ={@Index(columnList = "name", name = "name_ixd")} )
public class Student {


    Integer id;
    String name;
    String address;
    Integer age;
    String student_class;


    //Integer studentCode;
    String gender;
    String email;
    String phoneNumber;
    String birthday;

    public Student() {

    }

    public Student(Integer _id) {
        id = _id;
    }


    public Student(String name,String address, Integer age, String _class){
        this.name=name;
        this.address=address;
        this.age=age;
        this.student_class=_class;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "class")
    public String getStudent_class() {
        return student_class;
    }

    public void setStudent_class(String student_class) {
        this.student_class = student_class;
    }


    /*@Column(name = "student_code" , unique = true, nullable = false)
    public Integer getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(Integer studentCode) {
        this.studentCode = studentCode;
    }*/

    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "birthday")
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}

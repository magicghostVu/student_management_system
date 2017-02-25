package entities;

import javax.persistence.*;

/**
 * Created by magic_000 on 25/02/2017.
 */

@Entity
@Table(name = "students", uniqueConstraints = {@UniqueConstraint(columnNames = "id")},
        indexes ={@Index(columnList = "name", name = "name_ixd")} )
public class Student {
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String name;
    String address;
    Integer age;
    String student_class;

    public Student() {

    }

    public Student(Integer _id) {
        id = _id;
    }


    @Id
    @Column(name = "id")
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
}

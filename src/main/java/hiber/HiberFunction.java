package hiber;

import entities.Student;
import h.utils.HUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by magic_000 on 25/02/2017.
 */
public class HiberFunction {

    static SessionFactory sf= HUtils.getSessionFactory();
    public static Student getStudentById(Integer id){
        Session ss= sf.getCurrentSession();
        ss.getTransaction().begin();
        Student result=  ss.get(Student.class, id);
        ss.close();
        return  result;
    }
    public static boolean updateOrAddStudent(Student student){
        Session ss= sf.getCurrentSession();
        try{
            ss.getTransaction().begin();
            ss.saveOrUpdate(student);
            ss.getTransaction().commit();
            ss.close();
        }catch (Exception e){
            e.printStackTrace();
            ss.getTransaction().rollback();
            ss.close();
            return false;
        }
        return true;
    }
    public static List<Student> searchByName(String name){
        String hql= "select student from "+ Student.class.getName()+ " as student where student.name like '"+ name+"%'";
        Session ss= sf.getCurrentSession();
        ss.getTransaction().begin();
        Query<Student> q= ss.createQuery(hql);
        List<Student> result= q.getResultList();
        ss.close();
        return result;
    }
    public static List<Student> getAllStudent(){
        String hql= "select student from "+ Student.class.getName()+" as student";
        Session ss= sf.getCurrentSession();
        ss.getTransaction().begin();
        Query<Student> q= ss.createQuery(hql);
        List<Student> res= q.getResultList();
        ss.close();
        return res;
    }
}

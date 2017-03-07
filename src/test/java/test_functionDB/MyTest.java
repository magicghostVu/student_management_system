package test_functionDB;

import hiber.HiberFunction;
import junit.framework.TestCase;

/**
 * Created by magic_000 on 05/03/2017.
 */
public class MyTest extends TestCase {

    public void testDeleteStudent(){
        assertTrue(!HiberFunction.deleteAStudent(15));
    }

}

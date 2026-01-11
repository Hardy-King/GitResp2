import com.gk.entity.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test04 {

    private SqlSession sqlSession;
    @Before
    public void init(){
        SqlSessionFactoryBuilder ssfb =new SqlSessionFactoryBuilder();
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory factory=ssfb.build(resourceAsStream) ;
        sqlSession=factory.openSession();
    }

    @Test
    public void testInsert(){
        Emp emp = new Emp(7937,"安吉拉Baby","SALESMAN",7389, new Date(),3100.0,200.0,10);
        int rows = sqlSession.insert("addEmp", emp);
        System.out.println(rows);
        sqlSession.commit();
    }

    @Test
    public void testUpdate(){
        Emp emp =new Emp( );
        emp.setEname("晓明");
        emp.setEmpno(7937);
        int rows = sqlSession.update("updateEmp", emp);
        System.out.println(rows);
        sqlSession.commit();

    }

    @Test
    public void testDelete(){

        int rows = sqlSession.delete("deleteEmp", 7936);
        System.out.println(rows);
        sqlSession.commit();

    }



    @After
    public void release(){
        sqlSession.close();
    }
}

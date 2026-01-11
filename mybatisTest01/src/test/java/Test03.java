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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test03 {

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
    public void testFindOne(){

        // 调用SQL语句
        Emp e = sqlSession.selectOne("findByEmpno","7499");
        System.out.println(e);
    }

    @Test
    public void testFindMapArgs(){
        //Map集合作为参数
        Map<String,Object> args = new HashMap<String,Object>();
        args.put("deptno",20);
        args.put("sal",3000.0);
        List<Emp> list = sqlSession.selectList("findEmpByDeptnoAndSal",args);
       list.forEach(System.out::println);
    }

    @Test
    public void testEmpArgs(){
        //对象作为参数
        Emp args = new Emp();
        args.setDeptno(10);
        args.setSal(2000.0);
        List<Emp> emps = sqlSession.selectList("findEmpByDeptnoAndSal2", args);
        emps.forEach(System.out::println);
    }

    @After
    public void release(){
        sqlSession.close();
    }
}

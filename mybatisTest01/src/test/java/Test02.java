import com.gk.entity.Dept;
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
import java.util.List;
import java.util.Map;

public class Test02 {

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
        Emp e = sqlSession.selectOne("findOne");
        System.out.println(e);
    }

    @Test
    public void testFindAll(){
        List<Emp> list = sqlSession.selectList("findAll");
        for (Emp e:list){
            System.out.println(e);
        }
    }

    @Test
    public void testFindMap(){
        Map<Integer,Emp> empMap = sqlSession.selectMap("findEmpMap","EMPNO");
        for (Integer e:empMap.keySet()){
            System.out.println(e+":"+empMap.get(e));
        }
    }

    @After
    public void release(){
        sqlSession.close();
    }
}

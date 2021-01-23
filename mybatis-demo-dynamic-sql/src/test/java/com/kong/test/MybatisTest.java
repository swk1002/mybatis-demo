package com.kong.test;

import com.kong.dao.IUserDao;
import com.kong.domain.QueryVo;
import com.kong.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MybatisTest {


    private InputStream in;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init () throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建sqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂创建sqlSession对象
        session = factory.openSession();
//        session = factory.openSession(true);//设置自动提交
        //4.使用sqlSession创建Dao接口的代理对象
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {
        session.commit();//默认情况下关闭了自动提交，所以在这里需要手动执行一下commit
        //6.释放资源
        session.close();
        in.close();
    }


    @Test
    public void testFindByCondition(){
        User user = new User();
        user.setUsername("spark");
//        user.setAddress("beijing");//结果是空
        List<User> users = userDao.findByCondition(user);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }

    @Test
    public void testFindByIds(){
        QueryVo queryVo = new QueryVo();
        List<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(5);
        queryVo.setUserids(list);

        List<User> users = userDao.findUserInIds(queryVo);
        for (User user : users) {
            System.out.println(user);
        }
    }

}

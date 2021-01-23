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
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setUsername("spark3");
        user.setAddress("shanghai3");
        user.setSex("0");
        user.setBirthday(new Date());
        System.out.println("保存操作之前："+user);
        userDao.saveUser(user);
        System.out.println("保存操作之后："+user);
    }

    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setId(1);
        user.setUsername("jack1");
        user.setAddress("shanghai1");
        user.setSex("0");
        user.setBirthday(new Date());
        userDao.updateUser(user);
    }

    @Test
    public void testDeleteUser(){
        userDao.deleteUser(1);
    }

    @Test
    public void testFindById(){
        User user = userDao.findById(2);
        System.out.println(user);
    }

    @Test
    public void testFindByName(){
        List<User> users = userDao.findByName("%spark%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testCountAll(){
        int countAll = userDao.countAll();
        System.out.println(countAll);
    }

    @Test
    public void testFindByQueryVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%spark%");
        vo.setUser(user);
        List<User> users = userDao.findByQueryVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }

}

package com.kong.dao;

import com.kong.domain.QueryVo;
import com.kong.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

    /**
     * 插入数据
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新数据
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除数据
     * @param userid
     */
    void deleteUser(Integer userid);

    /**
     * 通过id去查询数据
     * @param userid
     * @return
     */
    User findById(Integer userid);

    /**
     * 根据名字模糊查询
     * @param username
     * @return
     */
    List<User> findByName(String username);

    /**
     * 聚合操作，统计表里共有多少条数据
     * @return
     */
    int countAll();

    /**
     * 根据QueryVo的条件查询用户
     * @param VO
     * @return
     */
    List<User> findByQueryVo(QueryVo VO);
}

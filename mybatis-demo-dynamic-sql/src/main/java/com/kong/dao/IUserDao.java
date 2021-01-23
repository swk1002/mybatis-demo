package com.kong.dao;

import com.kong.domain.QueryVo;
import com.kong.domain.User;

import java.util.List;

public interface IUserDao {

    /**
     * 根据传入的条件进行查询
     * @param user
     * @return
     */
    List<User> findByCondition(User user);

    /**
     * 根据queryVo中提供的用户列表(List)来进行查询
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);
}

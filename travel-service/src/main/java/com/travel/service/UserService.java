package com.travel.service;

import com.travel.common.StringUtil;
import com.travel.dao.UserMapper;
import com.travel.pojo.User;
import com.travel.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author chenzp
 * @email 84797939@qq.com
 * @@version 2018-08-09
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(User user){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(user.getEmail());
        List<User> users = userMapper.selectByExample(userExample);
        if(users.isEmpty()){// 没有注册  插入数据库  发送发送邮件的消息
            String uuid = StringUtil.getUuid();
            user.setId(uuid);
            user.setName(user.getEmail());
            user.setStatus(2); //已注册  没激活
            user.setCreateTime(new Date());
            userMapper.insertSelective(user);
            return user;
        }
        return users.get(0);
    }
}

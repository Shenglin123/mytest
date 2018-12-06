package com.itheima.ssm.service;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IuserDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.utils.BCryptPasswordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * @author 盛林
 * @date 2018/12/2
 */
@Transactional
@Service
public class userServiceI implements userService {
        @Autowired
        private IuserDao userDao;
       @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserInfo userInfo = null;
            try {
                userInfo = userDao.findByUsername(username);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //处理自己的用户对象封装成UserDetails
            //  User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
            User user = new User(userInfo.getUsername(), /*"{noop}" + */userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));
            return user;
        }

        //作用就是返回一个List集合，集合中装入的是角色描述
        public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

            List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
            for (Role role : roles) {
                list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            }
            return list;
        }
    @Override
    public UserInfo findById(String id) throws Exception{

        return  userDao.findById(id);
    }

    @Override
    public void delete(String id) {
          userDao.deleteuser(id);
          userDao.delete(id);
    }
    @Override
    public void update(UserInfo userInfo){
        UserInfo userInfo1 = userDao.findById(userInfo.getId());
        String password = userInfo1.getPassword();
        String password1 = userInfo.getPassword();
        if(password.equals(password1)){
            userDao.update(userInfo);
        }else {
            userInfo.setPassword(BCryptPasswordEncoderUtils.encodePassword(userInfo.getPassword()));
            userDao.update(userInfo);
        }

    }
    @Override
    public UserInfo findByUsername(String username){
        UserInfo byUsername = userDao.findByUsername(username);
        return byUsername;
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
        //对密码进行加密处理
        userInfo.setPassword(BCryptPasswordEncoderUtils.encodePassword(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public List<UserInfo> findAll(Integer pagenum,Integer pageSize) throws Exception {
        PageHelper.startPage(pagenum,pageSize);
        return userDao.findAll();
    }
    }


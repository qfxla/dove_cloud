package com.dove.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dove.authority.entity.User;
import com.dove.authority.entity.dto.UserLoginPasswordDto;
import com.dove.authority.entity.dto.UserLoginPhoneDto;
import com.dove.authority.entity.dto.UserRegisterDto;
import com.dove.authority.entity.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author run
 * @since 2021-03-18
 */
public interface UserService extends IService<User> {

    public boolean register(UserRegisterDto userDto, HttpServletRequest request, HttpServletResponse response);

    public boolean login(UserLoginPhoneDto userDto, HttpServletRequest request, HttpServletResponse response);

    public boolean login(UserLoginPasswordDto userDto, HttpServletRequest request, HttpServletResponse response);

    public boolean retrieve(UserRegisterDto userDto, HttpServletRequest request, HttpServletResponse response);

    public boolean updateUser(User user);

    public UserVo getUserInfo();

    public boolean joinEnterprise(String inviteCode);


}

package com.dove.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.authority.entity.vo.StaffVo;
import com.dove.authority.entity.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.dove.authority.entity.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author run
 * @since 2021-03-18
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT COUNT(*) FROM t_authority_user WHERE phone = #{phone}")
    public int findUserCountOfPhone(String phone);

    @Select("SELECT id, phone, username, password, sex, profile_photo, enterprise_id FROM t_authority_user WHERE phone = #{phone}")
    public User findUserByPhone(String phone);

    @Select("SELECT tau.id, phone, username, sex, profile_photo, birthday, " +
            "(SELECT name FROM t_authority_enterprise WHERE id = tau.enterprise_id) AS enterprise " +
            "FROM t_authority_user tau WHERE tau.id = #{userId} ")
    public UserVo findUserVoById(Long userId);

    @Select("SELECT user_id FROM t_authority_role_user WHERE role_id = #{roleId}")
    public List<Long> findUserOfRole(Long roleId);

    @Select("SELECT id, phone, username, sex, profile_photo FROM t_authority_user WHERE enterprise_id = #{enterpriseId}")
    public List<StaffVo> getStaffOfEnterprise(Long enterpriseId);

    @Select("SELECT id, phone, username, sex, profile_photo FROM t_authority_user WHERE " +
            "enterprise_id = #{enterpriseId} LIMIT #{startIndex}, #{size}")
    public List<StaffVo> getStaffOfEnterpriseAndPage(@Param("enterpriseId") Long enterpriseId
                                                    ,@Param("startIndex") Integer startIndex
                                                    ,@Param("size") Integer size);

    @Select("SELECT COUNT(*) FROM t_authority_user WHERE enterprise_id = #{enterpriseId}")
    public int getStaffCountOfEnterprise(Long enterpriseId);

    @Update("UPDATE t_authority_user SET enterprise_id = NULL WHERE id = #{userId} AND enterprise_id = #{enterpriseId}")
    public int shiftOutEnterprise(@Param("userId") Long userId,@Param("enterpriseId") Long enterpriseId);

    @Update("UPDATE t_authority_user SET enterprise_id = #{enterpriseId} WHERE enterprise_id IS NULL")
    public int setEnterpriseOfUser(@Param("userId") Long userId,@Param("enterpriseId") Long enterpriseId);

    @Update("UPDATE t_authority_user SET password = #{password} WHERE phone = #{phone}")
    public int updatePassword(@Param("password") String password,@Param("phone") String phone);

}

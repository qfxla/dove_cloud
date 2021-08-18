package com.dove.authority.mapper;

import com.dove.authority.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.authority.entity.bo.RoleBo;
import com.dove.authority.entity.vo.RoleVo;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author run
 * @since 2021-03-18
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("SELECT role_id FROM t_authority_role_user taru INNER JOIN t_authority_role tar ON " +
            "taru.user_id = #{userId} AND tar.id = taru.role_id AND tar.usable = 1 AND tar.is_deleted = 0")
    public List<Long> findRoleIdsOfUser(Long userId);

    @Select("SELECT COUNT(*) FROM t_authority_role WHERE name = #{name} AND enterprise_id = #{enterpriseId} " +
            "AND is_deleted = 0")
    public int getCountByNameAndEnterprise(@Param("name") String name, @Param("enterpriseId")Long enterpriseId);

    @Delete("DELETE FROM t_authority_role WHERE id = #{roleId} AND enterprise_id = #{enterpriseId}")
    public int deleteRoleByIdAndEnterprise(@Param("roleId") Long roleId, @Param("enterpriseId") Long enterpriseId);

    @Select("SELECT id, name, remarks, usable FROM t_authority_role WHERE enterprise_id = #{enterpriseId} " +
            "AND is_deleted = 0")
    public List<RoleVo> findRoleVoOfEnterprise(Long enterpriseId);

    @Select("SELECT tar.id, name, remarks, weight, usable FROM t_authority_role tar INNER JOIN t_authority_role_user taru " +
            "ON tar.id = taru.role_id AND taru.user_id = #{userId} AND tar.is_deleted = 0")
    public List<RoleVo> findRoleVoOfUser(Long userId);

    @Update("UPDATE t_authority_role SET name = #{role.name}, remarks = #{role.remarks} WHERE id = #{role.id} " +
            "AND enterprise_id = #{enterpriseId}")
    public int updateRole(@Param("role") RoleBo role, @Param("enterpriseId") Long enterpriseId);

    @Select("SELECT MIN(weight) FROM t_authority_role tar INNER JOIN t_authority_role_user taru " +
            "ON tar.id = taru.role_id AND taru.user_id = #{userId} AND tar.is_deleted = 0")
    //获取用户的最高权重
    public int getWeightestOfUser(Long userId);

    @Select("SELECT weight FROM t_authority_role WHERE id = #{roleId}")
    public int getWeightOfRole(Long roleId);

    @Delete("DELETE FROM t_authority_role_user WHERE user_id = #{userId}")
    public int deleteRoleOfUser(Long userId);

    @Insert("INSERT INTO t_authority_role_user VALUES(#{tableId},#{userId},#{roleId},NOW())")
    public int addRoleOfUser(@Param("roleId") Long roleId, @Param("userId") Long userId, @Param("tableId") Long tableId);

    public int getWeightestInRoles(List<Long> roleIds);

}

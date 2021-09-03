package com.dove.authority.mapper;

import com.dove.authority.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.authority.entity.vo.PermissionVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author run
 * @since 2021-03-18
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    @Select("SELECT tap.name FROM t_authority_role_permission tarp INNER JOIN t_authority_permission tap " +
            "ON tarp.role_id = #{roleId} AND tarp.permission_id = tap.id ")
    public List<String> getPermissionNameOfRole(Long roleId);

    @Select("SELECT permission_id FROM t_authority_role_permission WHERE role_id = #{roleId}")
    public List<Long> getPermissionIdOfRole(Long roleId);

    @Delete("DELETE FROM t_authority_role_permission WHERE role_id = #{roleId}")
    public int deletePermissionOfRole(Long roleId);

    @Insert("INSERT INTO t_authority_role_permission VALUES(#{tableId}, #{roleId}, #{permissionId}, NOW())")
    public int addPermissionOfRole(@Param("roleId") Long roleId
                                    , @Param("permissionId") Long permissionId
                                    , @Param("tableId") Long tableId);

    public int deletePermissionOfRole(@Param("permissions") List<Long> permissions,@Param("roleId") Long roleId);

    @Select("SELECT id, name, remarks, path FROM t_authority_permission WHERE parent_id = #{parentId}")
    public List<PermissionVo> getPermissionOfParent(Long parentId);

    @Select("SELECT tap.id, name, remarks, path FROM t_authority_permission tap INNER JOIN " +
            "t_authority_role_permission tarp ON tap.parent_id IS NULL AND tap.id = tarp.permission_id " +
            "AND tarp.role_id = #{roleId}")
    public List<PermissionVo> getRootPermissionOfRole(Long roleId);

    @Select("SELECT id FROM t_authority_permission")
    public List<Long> getAllPermission();

    public List<PermissionVo> getRootPermissionOfRoles(@Param("roleIds") List<Long> roleIds);

    public List<PermissionVo> getPermissionOfParentAndRoles(@Param("parentId") Long parentId
                                                , @Param("roleIds") List<Long> roleIds);

    @Select("SELECT id FROM t_authority_permission WHERE name LIKE #{prefix}")
    public List<Long> getPermissionOfPrefix(String prefix);

}

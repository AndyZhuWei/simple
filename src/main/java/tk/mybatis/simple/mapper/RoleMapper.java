package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.List;

@CacheNamespaceRef(RoleMapper.class)
public interface RoleMapper {

    @Select({"select id,role_name,enabled,create_by,create_time from sys_role where id=#{id}"})
    SysRole selectById(Long id);

    @Results({
         @Result(property="id",column="id",id=true),
         @Result(property="roleName",column="role_name"),
         @Result(property="enabled",column="enabled"),
         @Result(property="createBy",column="create_by"),
         @Result(property="createTime",column="create_time"),
    })
    @Select({"select id,role_name,enabled,create_by,create_time from sys_role where id=#{id}"})
    SysRole selectById2(Long id);



    @Select({"select * from sys_role"})
    List<SysRole> selectAll();

    /**
     * 不需要返回主键
     * @param sysRole
     * @return
     */
    @Insert({"insert into sys_role(id,role_name,enabled,create_by,create_time)",
             "values(#{id},#{roleName},#{enabled},#{createBy},",
             "#{createTime,jdbcType=TIMESTAMP})"})
    int insert(SysRole sysRole);


    /**
     * 返回自增主键
     * @param sysRole
     * @return
     */
    @Insert({"insert into sys_role(role_name,enabled,create_by,create_time)",
            "values(#{roleName},#{enabled},#{createBy},",
            "#{createTime,jdbcType=TIMESTAMP})"})
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert2(SysRole sysRole);

    /**
     * 返回非自增主键
     * @param sysRole
     * @return
     */
    @Insert({"insert into sys_role(role_name,enabled,create_by,create_time)",
            "values(#{roleName},#{enabled},#{createBy},",
            "#{createTime,jdbcType=TIMESTAMP})"})
    @SelectKey(statement="select last_insert_id()",
              keyProperty = "id",
              resultType = Long.class,
              before = false)
    int insert3(SysRole sysRole);

    @Update({"update sys_role",
             "set role_name=#{roleName},",
             "enabled=#{enabled},",
             "create_by=#{createBy},",
             "create_time=#{createTime,jdbcType=TIMESTAMP}",
              "where id=#{id}"
         })
    int updateById(SysRole sysRole);

    @Delete("delete from sys_role where id=#{id}")
    int deleteById(Long id);

    /**
     * 获取所有的角色及对应的所有权限
     */
    List<SysRole> selectAllRoleAndPrivileges();


    /**
     * 根据用户Id获取用户的角色信息
     *
     * @param userId
     * @return
     */
    List<SysRole> selectRoleByUserIdChoose(Long userId);

    List<SysRole> selectAll(RowBounds rowBounds);






}

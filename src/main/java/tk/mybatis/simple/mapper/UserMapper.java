package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * 通过id查询用户
     *
     * @param id
     * @return
     */
    SysUser selectById(Long id);
    /**
     * 查询全部用户
     *
     * @return
     *
     */
    List<SysUser> selectAll();
    /**
     * 根据用户id获取角色信息
     *
     * @param userId
     * @return
     */
    List<SysRole> selectRolesByUserId(Long userId);
    /**
     * 新增用户
     * @param sysUser
     * @return
     */
    int insert(SysUser sysUser);
    /**
     * 新增用户-使用useGenerateeys方式
     * @param sysUser
     * @return
     */
    int insert2(SysUser sysUser);
    /**
     * 新增用户-使用selectKey方式
     * @param sysUser
     * @return
     */
    int insert3(SysUser sysUser);
    /**
     * 根据主键更新
     *
     * @param sysUser
     * @return
     */
    int updateById(SysUser sysUser);
    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    int deleteById(Long id);
    /**
     * 通过主键删除
     *
     * @param sysUser
     * @return
     */
    int deleteById(SysUser sysUser);
    /**
     * 根据用户id和角色的enabled状态获取用户的角色
     * 如果在多个参数前不加@param,将会报错
     * @param userId
     * @param enabled
     * @return
     */
    List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId") Long userId,@Param("enabled") Integer enabled);

    /**
     * 根据用户和角色获取用户的角色
     * @param user
     * @param role
     * @return
     */
    List<SysRole> selectRolesByUserAndRole(@Param("user") SysUser user,@Param("role") SysRole role);


    /**
     * 根据动态条件查询用户信息
     *
     * @param sysUser
     * @return
     */
    List<SysUser> selectByUser(SysUser sysUser);
    /**
     * 根据主键更新
     *
     * @param sysUser
     * @return
     */
    int updateByIdSelective(SysUser sysUser);

    /**
     * 根据用户id或用户名查询
     *
     * @param sysUser
     * @return
     */
    SysUser selectByIdOrUserName(SysUser sysUser);

    /**
     * 根据用户id集合查询（list集合）
     * 默认foreach标签中的collection属性值为list
     * 推荐使用@Param来指定参数的名字
     * @param idList
     * @return
     */
    List<SysUser> selectByIdList(List<Long> idList);
    /**
     * 根据用户id集合查询（数组形式）
     * 默认foreach标签中的collection属性值为array
     * 推荐使用@Param来指定参数的名字
     * @param idArray
     * @return
     */
    List<SysUser> selectByIdList(Long[] idArray);
    /**
     * 批量插入用户信息
     *
     * @param userList
     * @return
     */
    int insertList(List<SysUser> userList);


    /**
     * 通过Map更新列
     * 这里没有通过@Param注解指定参数名，因而MyBatis在内部的上下文中使用了默认值
     * _parameter作为该参数的key,所以在XML中也使用了_parameter.
     * @param map
     * @return
     */
    int updateByMap(Map<String,Object> map);

    /**
     * 根据用户id获取用户信息和用户的角色信息
     *
     * @param id
     * @return
     */
    SysUser selectUserAndRoleById(Long id);

    /**
     * 根据用户id获取用户信息和用户的角色信息
     *
     * @param
     * @return
     */
    SysUser selectUserAndRoleById2(Long id);
    /**
     * 根据用户id获取用户信息和用户的角色信息
     *
     * @param
     * @return
     */
    SysUser selectUserAndRoleById3(Long id);

    /**
     * 根据用户id获取用户信息和用户的角色信息
     *
     * @param
     * @return
     */
    SysUser selectUserAndRoleByIdSelect(Long id);

    /**
     * 获取所有的用户及对应的所有角色
     *
     * @return
     */
    List<SysUser> selectAllUserAndRoles();

    /**
     * 通过嵌套查询获取指定用户的信息以及用户的角色和权限信息
     *
     * @param id
     * @return
     */
    SysUser selectAllUserAndRolesSelect(Long id);


















}

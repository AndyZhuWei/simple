package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;
import tk.mybatis.simple.type.Enabled;

import java.util.List;

public class RoleMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取RoleMapper接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //调用selectById方法，查询id=1的角色
            SysRole role = roleMapper.selectById(1L);
            //role不为空
            Assert.assertNotNull(role);
            //roleName=管理员
            Assert.assertEquals("管理员",role.getRoleName());
        } finally {
            //不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectById2() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取RoleMapper接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //调用selectById2方法，查询id=1的角色
            SysRole role = roleMapper.selectById2(1L);
            //role不为空
            Assert.assertNotNull(role);
            //roleName=管理员
            Assert.assertEquals("管理员",role.getRoleName());
        } finally {
            //不要忘记关闭sqlSession
            sqlSession.close();
        }
    }


    @Test
    public void testSelectAllUserAndRoles() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取RoleMapper接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> roleList = roleMapper.selectAllRoleAndPrivileges();
            System.out.println("角色数："+roleList.size());
            for(SysRole role : roleList) {
                System.out.println("角色名："+role.getRoleName());
                for(SysPrivilege privilege : role.getPrivilegeList()) {
                    System.out.println("权限名："+privilege.getPrivilegeName());
                }
            }
        } finally {
            //不要忘记关闭sqlSession
            sqlSession.close();
        }
    }


    @Test
    public void testSelectRoleByUserIdChoose() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取RoleMapper接口
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //由于数据库数据enable都是1，所以给其中一个角色的enable赋值0
            SysRole role = roleMapper.selectById(2L);
            role.setEnabled(Enabled.disabled);
            roleMapper.updateById(role);
            //获取用户1的角色
            List<SysRole> roleList = roleMapper.selectRoleByUserIdChoose(1L);
            for(SysRole r : roleList) {
                System.out.println("角色名："+r.getRoleName());
                if(r.getId().equals(1L)) {
                    //第一个角色存在权限信息
                    Assert.assertNotNull(r.getPrivilegeList());
                } else if(r.getId().equals(2L)) {
                    //第二个角色的权限信息为null
                    System.out.println(r.getCreateBy());
                    Assert.assertNull(r.getPrivilegeList());
                    continue;
                }
                for(SysPrivilege privilege : r.getPrivilegeList()) {
                    System.out.println("权限名："+privilege.getPrivilegeName());
                }
            }
        } finally {
            //不要忘记关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            //先查询出角色，然后修改角色的enabled值为disabled
            SysRole role = roleMapper.selectById(2L);
            Assert.assertEquals(Enabled.enabled,role.getEnabled());
            role.setEnabled(Enabled.disabled);
            roleMapper.updateById(role);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

}

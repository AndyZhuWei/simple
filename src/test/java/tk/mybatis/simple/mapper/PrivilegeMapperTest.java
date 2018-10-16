package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysPrivilege;
import tk.mybatis.simple.model.SysRole;

public class PrivilegeMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById() {
        //获取sqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //获取PrivilegeMapper接口
            PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);
            //调用selectById方法，查询id=1的权限
            SysPrivilege privilege = privilegeMapper.selectById(1L);
            //privilege不为空
            Assert.assertNotNull(privilege);
            //privilegeName=用户管理
            Assert.assertEquals("用户管理",privilege.getPrivilegeName());
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
            //获取PrivilegeMapper接口
            PrivilegeMapper privilegeMapper = sqlSession.getMapper(PrivilegeMapper.class);
            //调用selectById方法，查询id=1的权限
            SysPrivilege privilege = privilegeMapper.selectById2(1L);
            //privilege不为空
            Assert.assertNotNull(privilege);
            //privilegeName=用户管理
            Assert.assertEquals("用户管理", privilege.getPrivilegeName());
        } finally {
            //不要忘记关闭sqlSession
            sqlSession.close();
        }
    }
}

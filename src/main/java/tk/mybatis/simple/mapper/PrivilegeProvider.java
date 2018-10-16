package tk.mybatis.simple.mapper;

import org.apache.ibatis.jdbc.SQL;

public class PrivilegeProvider {
    /**
     * 拼接SQL语句时使用new SQL(){...}方法
     * @param id
     * @return
     */
    public String selectById(final Long id) {
        return new SQL(){
            {SELECT("id,privilege_name,privilege_url");
             FROM("sys_privilege");
             WHERE("id=#{id}");
            }
        }.toString();
    }

    /**
     * 可以直接返回sql语句
     * @param id
     * @return
     */
    public String selectById2(final Long id) {
        return "select id,privilege_name,privilege_url"+
                " from sys_privilege where id=#{id}";
    }

















}

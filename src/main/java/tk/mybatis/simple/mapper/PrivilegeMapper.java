package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.simple.model.SysPrivilege;

public interface PrivilegeMapper {

    @SelectProvider(type=PrivilegeProvider.class,method="selectById")
    SysPrivilege selectById(Long id);

    @SelectProvider(type=PrivilegeProvider.class,method="selectById2")
    SysPrivilege selectById2(Long id);
}

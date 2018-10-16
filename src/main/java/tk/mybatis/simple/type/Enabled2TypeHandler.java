package tk.mybatis.simple.type;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhuwei
 * @Date:2018/10/15 16:33
 * @Description: Enabled2类型处理器
 */
public class Enabled2TypeHandler implements TypeHandler<Enabled2> {
    private final Map<Integer,Enabled2> enabled2Map =
            new HashMap<Integer,Enabled2>();

    public Enabled2TypeHandler() {
        for(Enabled2 enabled2 : Enabled2.values()) {
            enabled2Map.put(enabled2.getValue(),enabled2);
        }
    }

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Enabled2 enabled2, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,enabled2.getValue());
    }

    @Override
    public Enabled2 getResult(ResultSet resultSet, String columnName) throws SQLException {
        Integer value = resultSet.getInt(columnName);
        return enabled2Map.get(value);
    }

    @Override
    public Enabled2 getResult(ResultSet resultSet, int columnIndex) throws SQLException {
        Integer value = resultSet.getInt(columnIndex);
        return enabled2Map.get(value);
    }

    @Override
    public Enabled2 getResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        Integer value = callableStatement.getInt(columnIndex);
        return enabled2Map.get(value);
    }
}

package tk.mybatis.simple.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import tk.mybatis.simple.model.Country;

import java.awt.image.ImagingOpException;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @Author: zhuwei
 * @Date:2018/10/11 16:48
 * @Description:
 */
public class CountryMapperTest extends BaseMapperTest{


    @Test
    public void testSelectAll() {
        SqlSession sqlSession = this.getSqlSession();
        try {
            List<Country> countryList = sqlSession.selectList("tk.mybatis.simple.mapper.CountryMapper.selectAll");
            printCountryList(countryList);
        } finally {
            //不要忘记关闭sqlSession
            sqlSession.clearCache();
        }
    }

    private void printCountryList(List<Country> countryList) {
        for(Country county : countryList) {
            System.out.printf("%-4d%4s%4s\n",
                    county.getId(),
                    county.getCountryname(),
                    county.getCountrycode());
        }
    }

}

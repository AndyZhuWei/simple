package tk.mybatis.simple.mapper;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.LoggingCache;
import org.apache.ibatis.cache.decorators.LruCache;
import org.apache.ibatis.cache.decorators.SerializedCache;
import org.apache.ibatis.cache.decorators.SynchronizedCache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.junit.Test;
import tk.mybatis.simple.model.Country;
import tk.mybatis.simple.plugin.SimpleInterceptor;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhuwei
 * @Date:2018/10/16 11:28
 * @Description:
 */
public class SimpleTest {

    @Test
    public void test() throws IOException,SQLException {
        /**
         * 第一部分，指定日志和创建配置对象
         */
        //使用log4j记录日志
        LogFactory.useLog4JLogging();
        //创建配置对象
        final Configuration config = new Configuration();
        //配置settings中的部分属性
        config.setCacheEnabled(true);
        config.setLazyLoadingEnabled(false);
        config.setAggressiveLazyLoading(true);
        /**
         * 第二部分，添加拦截器
         */
        SimpleInterceptor interceptor1 = new SimpleInterceptor("拦截器1");
        SimpleInterceptor interceptor2 = new SimpleInterceptor("拦截器2");
        config.addInterceptor(interceptor1);
        config.addInterceptor(interceptor2);
        /**
         * 第三部分，创建数据源和JDBC事务
         */
        UnpooledDataSource dataSource = new UnpooledDataSource();
        dataSource.setDriver("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mybatis_test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        Transaction transaction = new JdbcTransaction(dataSource,null,false);
        /**
         * 第四部分，创建Executor
         */
        final Executor executor = config.newExecutor(transaction);
        /**
         * 第五部分，创建SqlSession对象
         */
        StaticSqlSource sqlSource = new StaticSqlSource(
                config,"SELECT * FROM contry WHERE id = ?");
        /**
         * 第六部分，创建参数映射配置
         */
        //由于上面的SQL有一个参数id,因此这里需要提供ParameterMapping(参数映射)
        List<ParameterMapping> parameterMappings =
                new ArrayList<ParameterMapping>();
        //通过parameterMapping.Builder创建ParameterMapping
//        parameterMappings.add(new ParameterMapping.Builder(
//                config,
//                "id",
//                registry.getTypeHandler(Long.class)).build());
        parameterMappings.add(new ParameterMapping.Builder(
                config,
                "id",
                Long.class).build());
        ParameterMap.Builder paramBuilder = new ParameterMap.Builder(
                config,
                "defaultParameterMap",
                Country.class,
                parameterMappings);
        /**
         * 第七部分，创建结果映射
         */
        //创建结果映射配置
        ResultMap resultMap = new ResultMap.Builder(
                config,
                "defaultResultMap",
                Country.class,
                new ArrayList<ResultMapping>(){{
                    add(new ResultMapping.Builder(config,"id",
                            "id",Long.class).build());
                    add(new ResultMapping.Builder(config,"countryname",
                            "countryname",String.class).build());
//                    add(new ResultMapping.Builder(config,"countrycode",
//                            "countrycode",registry.getTypehandler(String.class)).build());
                    add(new ResultMapping.Builder(config,"countrycode",
                            "countrycode",String.class).build());
                }}
        ).build();
        /**
         * 第八部分，创建缓存对象
         */
        final Cache countryCache =
                new SynchronizedCache(              //同步缓存
                        new SerializedCache(         //序列化缓存
                                new LoggingCache(     //日志缓存
                                        new LruCache(  //最少使用缓存
                                                new PerpetualCache("country_cache")//持久缓存
                                        ))));
        /**
         * 第九部分，创建MappedStatement对象
         */
        MappedStatement.Builder msBuilder = new MappedStatement.Builder(
                config,
                "tk.mybatis.simple.SimpleMapper.selectCountry",
                sqlSource,
                SqlCommandType.SELECT);
        msBuilder.parameterMap(paramBuilder.build());
        List<ResultMap> resultMaps = new ArrayList<ResultMap>();
        resultMaps.add(resultMap);
        //设置返回值resultMap
        msBuilder.resultMaps(resultMaps);
        //设置缓存
        msBuilder.cache(countryCache);
        //创建ms
        MappedStatement ms = msBuilder.build();

        List<Country> countries = executor.query(ms,1L, RowBounds.DEFAULT,Executor.NO_RESULT_HANDLER);


    }
}

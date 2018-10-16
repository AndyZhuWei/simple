package tk.mybatis.generator;

import org.mybatis.generator.internal.DefaultCommentGenerator;

import java.util.Properties;

/**
 * 自己实现的注释生成器
 */
public class MyCommentGenerator extends DefaultCommentGenerator {
    /**
     * 由于默认实现类中的可配参数都没有提供给子类可以访问的方法，这里要定义一遍
     */
    private boolean suppressAllComments;

    private boolean addRemarkComments;

    /**
     * 设置用户配置的参数
     */
//    public void addConfigurationProperties(Properties properties) {
//        //先调用父类方法保证父类方法可以正常使用
//        super.addConfigurationProperties(properties);
//        cmkmcmmm
//    }

}

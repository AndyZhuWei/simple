package tk.mybatis.simple.plugin;

import org.apache.ibatis.session.RowBounds;

/**
 * @Author: zhuwei
 * @Date:2018/10/16 15:49
 * @Description: 可以记录total的分页参数
 */
public class PageRowBounds extends RowBounds{
    private long total;

    public PageRowBounds() {
        super();
    }

    public PageRowBounds(int offset, int limit) {
        super(offset, limit);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}

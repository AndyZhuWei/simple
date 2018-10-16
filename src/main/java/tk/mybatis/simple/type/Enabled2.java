package tk.mybatis.simple.type;

/**
 * @Author: zhuwei
 * @Date:2018/10/15 16:14
 * @Description:
 */
public enum Enabled2 {
    enabled(1),//启用
    disabled(0);//禁用

    private final int value;

    private Enabled2(int value) {
        this.value=value;
    }
    public int getValue() {
        return value;
    }
}

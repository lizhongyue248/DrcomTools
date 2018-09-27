package cn.echocow.drcom.utils;

/**
 * Constant
 *      常量
 * @author echo
 * @version 1.0
 * @date 18-9-27 下午1:12
 */
public enum Constant {
    /**
     * 登录 URL
     */
    URL(""),

    /**
     * 成功代码
     */
    SUCCESS("200"),

    /**
     * 成功时，请求体长度
     */
    SUCCESS_LENGTH("5158"),

    /**
     * 失败时，请求体长度
     */
    FAIL_LENGTH("6945");


    private String data;

    Constant(String date) {
        this.data = date;
    }

    public String getData() {
        return data;
    }

    public int getInt(){
        return Integer.parseInt(data);
    }

}

package utils;

import cn.echocow.drcom.utils.Md5Util;
import org.junit.jupiter.api.Test;

/**
 * Md5Test
 *
 * @author echo
 * @version 1.0
 * @date 18-9-26 下午3:14
 */
class Md5Test {
    /**
     * test : 123
     * result : 0c969281446d076af52422b2b2717d35123456782
     */
    @Test
    void test() {
        String str = "123";
        System.out.println(Md5Util.getPassword(str));
    }
}

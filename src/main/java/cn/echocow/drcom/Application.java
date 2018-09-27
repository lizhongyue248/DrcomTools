package cn.echocow.drcom;

import cn.echocow.drcom.utils.Runner;

/**
 * Application
 *      启动类，一次只能启动一个服务
 *      先启动 server，再启动 web，都可以启动多个
 *      需要关闭 Single instance only
 *      你也可以直接在 verticle 里面启动（推荐）
 * @author echo
 * @version 1.0
 * @date 18-9-27 下午2:07
 */
public class Application {
    public static void main(String[] args) {
        /*
          启动 server
         */
        Runner.runServer();
        /*
          启动 web
         */
//        Runner.runWeb();
    }
}

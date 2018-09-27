package cn.echocow.drcom.web;

import cn.echocow.drcom.utils.Md5Util;
import cn.echocow.drcom.utils.Runner;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * WebVerticle
 *      web 端，event bus 持续发送请求
 *
 * @author echo
 * @version 1.0
 * @date 18-9-27 上午11:17
 */
public class WebVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerVerticle.class);
    private long userName = 5014933;

    /**
     * web 启动入口
     *      当运行多个时，请关闭 idea 的 Single instance only
     */
    public static void main(String[] args) {
        Runner.runHazelcast(WebVerticle.class);
    }

    @Override
    public void start() {
        EventBus eb = vertx.eventBus();
        JsonObject data = new JsonObject();
        data.put("DDDDD", "")
                .put("upass", "")
                .put("R1", "0")
                .put("R2", "1")
                .put("para", "00")
                .put("0MKKey", "123456")
                .put("v6ip", "");
        vertx.setPeriodic(10, handle -> {
            data.put("DDDDD", "0" + userName)
                    .put("upass", Md5Util.getPassword("" + userName));
            eb.send(ServerVerticle.class.getName(), data);
            userName ++;
        });

    }
}

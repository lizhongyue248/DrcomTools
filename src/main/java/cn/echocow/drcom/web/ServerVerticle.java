package cn.echocow.drcom.web;

import cn.echocow.drcom.utils.Constant;
import cn.echocow.drcom.utils.Runner;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

import java.util.stream.Collectors;

/**
 * ServerVerticle
 *      server 端，发送请求
 *
 * @author echo
 * @version 1.0
 * @date 18-9-27 上午10:55
 */
public class ServerVerticle extends AbstractVerticle {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerVerticle.class);

    /**
     * Server 启动入口
     *      当运行多个时，请关闭 idea 的 Single instance only
     */
    public static void main(String[] args) {
        Runner.runHazelcast(ServerVerticle.class);
    }

    /**
     * 当验证成功时：
     *      响应体长度  5158
     *      div#info  您已成功登录
     * 当验证失败时：
     *      响应体长度  6945
     *      div#info  无内容
     * <p>
     * 打印请求体
     *      System.out.println(result.bodyAsString("GB2312"));
     *
     */
    @Override
    public void start() {
        EventBus eb = vertx.eventBus();

        WebClient web = WebClient.create(vertx);
        eb.<JsonObject>consumer(this.getClass().getName(), message -> {
            JsonObject body = message.body();
            String data = body.stream()
                    .map(object -> object.getKey() + "=" + object.getValue())
                    .collect(Collectors.joining("&"));

            web.postAbs(Constant.URL.getData())
                    .sendBuffer(Buffer.buffer(data), ar -> {
                        if (ar.succeeded()) {
                            HttpResponse<Buffer> result = ar.result();
                            if (result.statusCode() == Constant.SUCCESS.getInt()) {
                                if (result.body().length() == Constant.SUCCESS_LENGTH.getInt()) {
                                    LOGGER.error("Login Success! Account: " + body.getString("DDDDD"));
                                } else {
                                    LOGGER.info("Login Failed! Account: " + body.getString("DDDDD"));
                                }
                            } else {
                                LOGGER.error("Server Web Post Failed!" + data + " --- " + ar.cause());
                            }
                        } else {
                            LOGGER.error("Server Web Failed!" + data + " --- " + ar.cause());
                        }
                    });

        });


    }
}

package com.javabeast.udpserver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.Environment;
import reactor.io.encoding.StandardCodecs;
import reactor.net.netty.udp.NettyDatagramServer;
import reactor.net.tcp.support.SocketUtils;
import reactor.net.udp.DatagramServer;
import reactor.net.udp.spec.DatagramServerSpec;

import java.util.concurrent.CountDownLatch;

/**
 * Created by jeffreya on 02/11/2016.
 *
 */

@Configuration
public class UDPServer {

    private Log log = LogFactory.getLog(UDPServer.class);

    @Bean
    public DatagramServer<byte[], byte[]> datagramServer(Environment env) throws InterruptedException {

        final int availableUdpPort = SocketUtils.findAvailableUdpPort();

        final DatagramServer<byte[], byte[]> server = new DatagramServerSpec<byte[], byte[]>(NettyDatagramServer.class)
                .env(env)
                .listen(availableUdpPort)
                .codec(StandardCodecs.BYTE_ARRAY_CODEC)
                .consumeInput(bytes -> {
                    log.info("received: " + new String(bytes));
                })
                .get();

        server.start().await();
        return server;
    }

    @Bean
    public CountDownLatch latch() {
        return new CountDownLatch(1);
    }
}
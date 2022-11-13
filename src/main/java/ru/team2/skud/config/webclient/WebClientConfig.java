package ru.team2.skud.config.webclient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Configuration
@PropertySource("classpath:configs/services.properties")
public class WebClientConfig {

    @Value("${telegram.url}")
    public String TELEGRAM_BASE_URL;

    public static final int TIMEOUT = 1000;

    public TcpClient createTcpClient() {
        return TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
                });
    }

    @Bean
    public WebClient webClientTelegram() {
        return WebClient.builder()
                .baseUrl(TELEGRAM_BASE_URL)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(createTcpClient())))
                .build();
    }
}
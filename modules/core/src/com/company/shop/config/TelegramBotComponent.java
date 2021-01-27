package com.company.shop.config;

import com.haulmont.cuba.security.app.Authenticated;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

@Component
public class TelegramBotComponent {
    @Inject
    private Logger log;

    @Inject
    private TgConfig bot;

    @Authenticated
    @PostConstruct
    protected void init() {
        //инициализируйте конфигурацию здесь
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            log.error("Error", e);
        } finally {
        }
    }

    @PreDestroy
    protected void closeSession() {
        //де-инициализируйте конфигурацию здесь если есть такая необходимость
        //например закройте connection если таковой имеется
    }
}

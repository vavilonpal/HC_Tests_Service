package org.combs.micro.hc_tests_service.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;

@Configuration
@Async
public class AsyncConfig {
}

/*
* todo
*  1) Добавть логику таймера на заверешенеие теста
*  2) Подумать как уже над тем чтобы студент мог начинать тест через свой микросервис
*  3) Кэшировать указанный ответ и вопросы, чтобы при переключении между вопросами
*   не делать доп запрос в базу данных
*   4) Подключить раббит мк чтобы:
*      1) Можно отправлять сообщения на сохранение вопросов и обновления, чтобы не нагружать бэк
*      .....
* */
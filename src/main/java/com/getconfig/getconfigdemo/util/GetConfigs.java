package com.getconfig.getconfigdemo.util;

import com.getconfig.getconfigdemo.dto.Config;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс, который при запуске данного эмулятора, посылает запрос в вебсервис с конфигами
 * и получает List<Config>. Далее mapWithConfigs сранивает ключ с именем Конфига и при
 * совпадении копирует значение.
 *
 * mapWithConfigs - мапа, ключ у которой должен в точности совпадать с именем конфига на вебсервисе.
 * Иначе она не заполнится как надо.
 *
 * nameOfEmulator - поле в которое нужно вписать имя данного эмулятора, что бы оно точно совпадало с именем
 * нужного эмулятора в вебсервисе с конфигами
 */
@Component
public class GetConfigs {
    private Map<String, String> mapWithConfigs = new HashMap<>();
    private String nameOfEmulator = "Сюда подставляем имя эмулятора, точ-в-точ как в вебсервисе";

    /**
     * Метод срабатывает при запуске эмулятора и получает List<Config> от вебсервиса с эмуляторами
     */
    @PostConstruct
    public void getConfigsMethod() {
        WebClient webClient = WebClient.create("http://localhost:8080");
        Mono<List<Config>> response = webClient.get()
                .uri("emulators/getEmulator/" + nameOfEmulator)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Config>>() {
                });

        List<Config> configs = response.block();

        fillMapWithConfig(configs);
    }

    /**
     * Метода вызывается из getConfigsMethod и заполняет мапу.
     * Делаем put ровно столько, сколько конфигов должно быть в данном эмуляторе.
     * Ключ должен в точности совпадать с именем конфига на вебсервисе
     *
     * @param configs - передаем List<Config> , который получили от вебсервиса с конфигами.
     */
    public void fillMapWithConfig(List<Config> configs) {
        mapWithConfigs.put("Сюда вставляем имя конфига", "Здесь будет его значение");

        for (int i = 0; i < configs.size(); i++) {
            if (mapWithConfigs.containsKey(configs.get(i).getNameConfig())) {
                mapWithConfigs.put(configs.get(i).getNameConfig(), configs.get(i).getSpecificationConfig());
            }
        }
    }
}

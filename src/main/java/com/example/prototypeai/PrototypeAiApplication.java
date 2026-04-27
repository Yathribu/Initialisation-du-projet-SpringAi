package com.example.prototypeai;

import com.example.prototypeai.ai.client.AskAi;
import com.example.prototypeai.util.enums.RequestType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class PrototypeAiApplication {

	public static void main(String[] args) {
        SpringApplication.run(PrototypeAiApplication.class, args);
	}

    @Bean
    public Map<RequestType, AskAi> mapAiProvider(List<AskAi> askAiList) {
        Map<RequestType, AskAi> map = new HashMap<>();

        for (AskAi askAi : askAiList) {
            map.put(askAi.getProvider(), askAi);
        }
        return map;
    }

}

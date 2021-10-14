package spring.telegram.bot.advice.processor.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import spring.telegram.bot.advice.entity.Advice;
import spring.telegram.bot.advice.processor.AdviceProcessor;

import java.util.Optional;

@Component
public class AdviceProcessorImpl implements AdviceProcessor {

    @Value("${source.url}")
    private String sourceUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getAdvice() {
        ResponseEntity<Advice> result = restTemplate.getForEntity(sourceUrl, Advice.class);
        return Optional.ofNullable(result)
                .map(HttpEntity::getBody)
                .map(Advice::getText)
                .orElse("");
    }
}

package com.example.demo.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RestController
public class Scheduler {
		
    @GetMapping("/send")
    @Scheduled(cron = "30/30 * * * * ?") // 1�и��� Ȯ��
    public void scronJobSch() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        log.info("Test = {}",strDate);
        
        RestTemplate restTemplate = new RestTemplate();

        Map<String,Object> request = new HashMap<String,Object>();
        request.put("username", "slackbot");
        request.put("text", strDate);

        HttpEntity<Map<String,Object>> entity = new HttpEntity<Map<String,Object>>(request);
                    
        String url = "https://hooks.slack.com/services/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"; // ����� ������ Webhook URL �ֱ�
        //String url = "https://hooks.slack.com/services/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"; // ����� ������ Webhook URL �ֱ�

        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }
    
}
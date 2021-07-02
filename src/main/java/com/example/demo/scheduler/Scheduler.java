package com.example.demo.scheduler;

//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
//@Slf4j
@Component
public class Scheduler {
	@GetMapping("/send")
	 public void send(){
        RestTemplate restTemplate = new RestTemplate();

        Map<String,Object> request = new HashMap<String,Object>();
        request.put("username", "sample");
        request.put("text", "custom-slack-msg");

        HttpEntity<Map<String,Object>> entity = new HttpEntity<Map<String,Object>>(request);
                    
        String url = "https://hooks.slack.com/services/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"; // 사용할 슬랙의 Webhook URL 넣기

        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

    }
	/*
    @Scheduled(cron = "0/10 * * * * ?") // 10초마다 확인 
    public void scronJobSch() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        log.info("Java cron job Test = {}",strDate);
    }
    
    */
}
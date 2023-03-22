package com.meli.ipinspector.automated;

import com.meli.ipinspector.dto.DailyPriceDto;
import com.meli.ipinspector.service.IpInspectorService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Data @AllArgsConstructor @NoArgsConstructor
public class AutomatedProcess {

    private IpInspectorService inspectorService;

    @Scheduled(cron = "0 0 */1 * * *")
    public void refreshDailyPriceForCurrentHour(){
        System.out.println("Going to currency API and getting the prices for countries " +
                "that close markets at the current hour");
    }



}

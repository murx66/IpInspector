package com.meli.ipinspector.automated;

import com.meli.ipinspector.dto.DailyPriceDto;
import com.meli.ipinspector.service.IpInspectorService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data @AllArgsConstructor @NoArgsConstructor
public class AutomatedProcess {

    private IpInspectorService inspectorService;

    public void refreshForCurrentHour(){
        Map<String, DailyPriceDto> currentUpdate = updateDailyPricePerHour();
        inspectorService.updateDailyPriceCache(currentUpdate);
    }

    private Map<String, DailyPriceDto>  updateDailyPricePerHour(){
        return null;
    }

}

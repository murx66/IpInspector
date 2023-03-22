package com.meli.ipinspector.helper;

import com.meli.ipinspector.dto.DailyPriceDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

public class DailyPriceHelper {

    public Map<String, DailyPriceDto> dailyPriceDummyLoad(Map<String, DailyPriceDto> cache){

        DailyPriceDto first = DailyPriceDto.builder()
                .country("Uruguay")
                .dailyPrice(39.9)
                .dailyPriceDate(LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC")))
                .build();
        cache.put("Uruguay", first);

        DailyPriceDto second = DailyPriceDto.builder()
                .country("Argentina")
                .dailyPrice(375)
                .dailyPriceDate(LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC")))
                .build();
        cache.put("Argentina", second);

        DailyPriceDto third = DailyPriceDto.builder()
                .country("USA")
                .dailyPrice(1)
                .dailyPriceDate(LocalDateTime.ofInstant(Instant.now(), ZoneId.of("UTC")))
                .build();
        cache.put("USA", third);

        return cache;
    }

}

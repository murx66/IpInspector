package com.meli.ipinspector.service;

import com.meli.ipinspector.dto.BlacklistedIpsDto;
import com.meli.ipinspector.dto.CountryDto;
import com.meli.ipinspector.dto.DailyPriceDto;
import com.meli.ipinspector.dto.SuccessfulIpInspectionResponseDto;
import com.meli.ipinspector.dto.response.CountryResponseDto;
import com.meli.ipinspector.exception.IpInspectorException;
import com.meli.ipinspector.helper.BlackListHelper;
import com.meli.ipinspector.helper.DailyPriceHelper;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
@Data @AllArgsConstructor @NoArgsConstructor
public class IpInspectorService {

    @PostConstruct
    private void init() {
        dailyPriceHelper = new DailyPriceHelper();
        dailyPriceHelper.dailyPriceDummyLoad(dailyPriceCache);
//        blackListHelper = new BlackListHelper();
//        blackListHelper.addIPToBlacklist("123.123.123.123");
//        blacklistedIps.add("123.123.123.123");
    }

    private Map<String, SuccessfulIpInspectionResponseDto> ipCache = new HashMap<>();
    private Map<String, DailyPriceDto> dailyPriceCache = new HashMap<>();

    @Autowired
    private BlackListHelper blackListHelper;
    private DailyPriceHelper dailyPriceHelper;


    public SuccessfulIpInspectionResponseDto getIpInformation(String ip) throws IpInspectorException {
        if(isIpBloqued(ip)){
            log.info(String.format("Blacklisted IP %s try to access the system successfully blocked!", ip));
            throw new IpInspectorException("Current IP is blacklisted in the application.");
        }
        SuccessfulIpInspectionResponseDto response = getFromCache(ip);
        if(response == null){
            CountryDto country = retrieveCountryByIp(ip);
            DailyPriceDto dailyPrice = getCotizationFromCache(country.getName());
            response = SuccessfulIpInspectionResponseDto.builder()
                    .country(country)
                    .dailyPrice(dailyPrice.getDailyPrice())
                    .build();
            ipCache.put(ip,response);
        }
        return response;
    }

    public void updateDailyPriceCache(Map<String, DailyPriceDto> actualizations){
        for(String current : actualizations.keySet()){
            dailyPriceCache.get(current).setDailyPrice(actualizations.get(current).getDailyPrice());
        }
        log.info("Cotizations cache updated successfully at ".concat(Instant.now().toString()));
    }

    public void addToBlackList(String ip) throws IpInspectorException {
        blackListHelper.addIPToBlacklist(ip);
    }

    private SuccessfulIpInspectionResponseDto getFromCache(String ip){
        return ipCache.get(ip);
    }

    private boolean isIpBloqued(String ip){
        if(blackListHelper.getBlackList().isEmpty()) return false;
        return blackListHelper.getBlackList().contains(ip) ? true : false;
    }

    private DailyPriceDto getCotizationFromCache(String key){
        return dailyPriceCache.get(key);
    }

    private CountryDto retrieveCountryByIp(String ip){

        WebClient webClient = WebClient.create();
        CountryResponseDto response = webClient.get()
                .uri("http://localhost:8090/countries/".concat(ip))
                .retrieve()
                .bodyToMono(CountryResponseDto.class)
                .block();

        return CountryDto.builder().name(response.getName()).officialCurrency(response.getOfficialCurrency())
                .officialLanguage(response.getOfficialLanguage()).build();
    }



}


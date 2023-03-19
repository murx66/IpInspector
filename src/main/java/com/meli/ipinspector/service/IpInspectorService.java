package com.meli.ipinspector.service;

import com.meli.ipinspector.dto.BlacklistedIpsDto;
import com.meli.ipinspector.dto.CountryDto;
import com.meli.ipinspector.dto.DailyPriceDto;
import com.meli.ipinspector.dto.SuccessfulIpInspectionResponseDto;
import com.meli.ipinspector.exception.IpInspectorException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
@Data @NoArgsConstructor @AllArgsConstructor
public class IpInspectorService {

    private Map<String, SuccessfulIpInspectionResponseDto> ipCache;
    private Map<String, DailyPriceDto> dailyPriceCache;
    private List<String> blacklistedIps;


    public SuccessfulIpInspectionResponseDto getIpInformation(String ip) throws IpInspectorException {
        if(isIpBloqued(ip)){
            log.info(String.format("Blacklisted IP %s try to access the system successfully blocked!", ip));
            throw new IpInspectorException("Current IP is blacklisted in the application.");
        }
        SuccessfulIpInspectionResponseDto response = getFromCache(ip);
        if(response == null){
            //Consultar la API de paises
            CountryDto country = null;

            DailyPriceDto dailyPrice = getCotizationFromCache(country.getName());
            DailyPriceDto.builder()
                    .country(country.getName())
                    .cotization(dailyPrice.getCotization())
                    .cotizationDate(dailyPrice.getCotizationDate())
                    .build();
        }
        return response;
    }

    public void updateDailyPriceCache(Map<String, DailyPriceDto> actualizations){
        for(String current : actualizations.keySet()){
            dailyPriceCache.get(current).setCotization(actualizations.get(current).getCotization());
        }
        log.info("Cotizations cache updated successfully at ".concat(Instant.now().toString()));
    }

    public BlacklistedIpsDto getCurrentBlacklistedIps(){
        return BlacklistedIpsDto.builder().blacklist(this.blacklistedIps).build();
    }
    private SuccessfulIpInspectionResponseDto getFromCache(String ip){
        return ipCache.get(ip);
    }

    private boolean isIpBloqued(String ip){
        return blacklistedIps.contains(ip) ? true : false;
    }

    private DailyPriceDto getCotizationFromCache(String key){
        return dailyPriceCache.get(key);
    }



}

package com.meli.ipinspector.service;

import com.meli.ipinspector.dto.MyDto;
import com.meli.ipinspector.exception.IpInspectorException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Data @NoArgsConstructor @AllArgsConstructor
public class IpInspectorService {

    private Map<String, MyDto> ipCache;
    private List<String> blacklistedIps;

    public MyDto getIpInformation(String ip) throws IpInspectorException {
        if(isIpBloqued(ip)) return
        return null;
    }

    private MyDto getFromCache(String ip){
        return ipCache.get(ip);
    }

    public boolean isIpBloqued(String ip){
        return blacklistedIps.contains(ip) ? true : false;
    }

}

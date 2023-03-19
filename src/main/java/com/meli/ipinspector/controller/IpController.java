package com.meli.ipinspector.controller;

import com.meli.ipinspector.dto.BlacklistedIpsDto;
import com.meli.ipinspector.dto.ErrorIpInspectorResponseDto;
import com.meli.ipinspector.dto.IpInspectionResponseDto;
import com.meli.ipinspector.dto.SuccessfulIpInspectionResponseDto;
import com.meli.ipinspector.exception.IpInspectorException;
import com.meli.ipinspector.service.IpInspectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ips")
public class IpController {

    private IpInspectorService inpectorService;

    public IpController(IpInspectorService inpectorService) {
        this.inpectorService = inpectorService;
    }

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<IpInspectionResponseDto> getIpInformation(@PathVariable String ip){
        try{
            return ResponseEntity.ok(inpectorService.getIpInformation(ip));
        }
        catch(IpInspectorException iie){
            return ResponseEntity.internalServerError().body(ErrorIpInspectorResponseDto.builder().errorMessage(iie.getMessage()););
        }
    }

    @GetMapping("/blacklist")
    @ResponseBody
    public ResponseEntity<BlacklistedIpsDto> getBlacklistedIps(){
        return ResponseEntity.ok(inpectorService.getCurrentBlacklistedIps());
    }

}

package com.meli.ipinspector.controller;

import com.meli.ipinspector.dto.MyDto;
import com.meli.ipinspector.exception.IpInspectorException;
import com.meli.ipinspector.service.IpInspectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("inspector")
public class IpController {

    private IpInspectorService inpectorService;

    public IpController(IpInspectorService inpectorService) {
        this.inpectorService = inpectorService;
    }

    @GetMapping("/information")
    @ResponseBody
    public ResponseEntity<MyDto> getIpInformation(@PathVariable String ip){
        try{
            return ResponseEntity.ok(inpectorService.getIpInformation(ip));
        }
        catch(IpInspectorException iie){
            return ResponseEntity.internalServerError().body(iie.getMessage());
        }
    }
}

package com.meli.ipinspector.helper;

import com.meli.ipinspector.exception.IpInspectorException;
import com.meli.ipinspector.security.StatusUpdater;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BlackListHelper {

    private List<String> blacklistedIps = new ArrayList<>();
    private PropertyChangeSupport support;
    private String currentToBlock;

    @PostConstruct
    private void init(){
        support = new PropertyChangeSupport(this);
        support.addPropertyChangeListener(new StatusUpdater());
    }

    public void addIPToBlacklist(String blacklistedIp) throws IpInspectorException {
        if(blacklistedIps.contains(blacklistedIp)) throw new IpInspectorException("The IP it's already blocked");
        support.firePropertyChange("currentToBlock", this.currentToBlock, blacklistedIp);
        this.blacklistedIps.add(blacklistedIp);
    }

    public List<String> getBlackList(){
        return this.blacklistedIps;
    }

}


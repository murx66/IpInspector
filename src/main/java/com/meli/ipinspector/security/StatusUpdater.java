package com.meli.ipinspector.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class StatusUpdater implements PropertyChangeListener {

    private String currentToBlock;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //Send message by sqs, mail or something
        System.out.println(String.format("Sending the message to a distributed list about ip %s it's being blocked", evt.getNewValue()));
    }

}

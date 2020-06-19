package com.example.demo.common.appender;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.MDC;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AsyncAppenderBase;

public class MyAsyncAppender extends AsyncAppenderBase<ILoggingEvent> {
    private boolean includeCallerData = false;

    /**
     * @return the includeCallerData
     */
    public boolean isIncludeCallerData() {
        return this.includeCallerData;
    }

    /**
     * @param includeCallerData the includeCallerData to set
     */
    public void setIncludeCallerData(boolean includeCallerData) {
        this.includeCallerData = includeCallerData;
    }
    
    protected void preprocess(ILoggingEvent event) {
        event.prepareForDeferredProcessing();
        if (this.includeCallerData) {
            @SuppressWarnings("unused")
            StackTraceElement[] StackTraceElement = event.getCallerData();
        }
    }
    protected boolean isDiscardable(ILoggingEvent event) {
        return event.getLevel().toInt() <= Level.INFO_INT;
    }
    private Map<String, List<ILoggingEvent>> map = new ConcurrentHashMap<String, List<ILoggingEvent>>();
    
    @Override
    protected void append(ILoggingEvent event) {
        String content = event.getFormattedMessage();
        String uuid = MDC.get("sequence");
        if (uuid == null) {
            uuid = event.getMDCPropertyMap().get("sequence");
        }
        if (uuid == null) {
            super.append(event);
            return ;
        }
        String msgLevel = event.getLevel().toString();
        System.out.println("===>" + Thread.currentThread().getId() + "," +
        Thread.currentThread().getName() + "," +
        System.identityHashCode(this) + ",{append}content=" + content + ",uuid=" + uuid +
        "msgLevel=" + msgLevel);

        if (content != null && 
            content.contains("********************************************************************************\n\n") &&
            !msgLevel.contains("ERROR")) {
            List<ILoggingEvent> events = map.get(uuid);
            if (events != null) {
                for(ILoggingEvent ievent : events) {
                    super.append(ievent);
                }
            }
            super.append(event);
            map.remove(uuid);
        } else {
            List<ILoggingEvent> events = map.get(uuid);
            if (events == null) {
                events = new CopyOnWriteArrayList<ILoggingEvent>();
            }
            events.add(event);
            map.put(uuid, events);
        }
    }
}

package com.example.demo.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public class Detail {
    private int state;
    private String message;

    private Map<Object, Object> map;

    public Detail() {
        this.state = 200;
        this.message = "success";
        this.map = new HashMap<>();
    }
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<Object, Object> getMap() {
        return map;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", map=" + map +
                '}';
    }
}

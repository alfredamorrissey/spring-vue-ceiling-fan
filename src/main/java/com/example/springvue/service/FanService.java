package com.example.springvue.service;

import com.example.springvue.model.Fan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FanService {

    private final Map<Long, Fan> fanStore = new HashMap<>();
    private long nextId = 1;

    public List<Fan> getAllFans() {
        return new ArrayList<>(fanStore.values());
    }

    public Fan getFanById(Long id) {
        return fanStore.get(id);
    }

    public Fan createFan(Fan fan) {
        fan.setId(nextId++);
        fanStore.put(fan.getId(), fan);
        return fan;
    }

    public Fan updateFan(Long id, Fan fan) {
        if (fanStore.containsKey(id)) {
            fan.setId(id);
            fanStore.put(id, fan);
            return fan;
        }
        return null;
    }

    public boolean deleteFan(Long id) {
        return fanStore.remove(id) != null;
    }

    public void deleteAllFans() {
        fanStore.clear();
    }
}

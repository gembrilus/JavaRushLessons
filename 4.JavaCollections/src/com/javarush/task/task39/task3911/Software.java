package com.javarush.task.task39.task3911;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Software {
    int currentVersion;

    private Map<Integer, String> versionHistoryMap = new LinkedHashMap<>();

    public void addNewVersion(int version, String description) {
        if (version > currentVersion) {
            versionHistoryMap.put(version, description);
            currentVersion = version;
        }
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public Map<Integer, String> getVersionHistoryMap() {
        return Collections.unmodifiableMap(versionHistoryMap);
    }

    public boolean rollback(int rollbackVersion) {
        String versionName = versionHistoryMap.get(rollbackVersion);
        if (versionName == null) {
            return false;
        } else {
            currentVersion = rollbackVersion;
            Iterator<Map.Entry<Integer, String>> it = versionHistoryMap.entrySet().iterator();
            while (it.hasNext()){
                Integer key = it.next().getKey();
                if (key > currentVersion){
                    it.remove();
                }
            }
            return true;
        }
    }
}

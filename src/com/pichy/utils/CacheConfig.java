package com.pichy.utils;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Deprecated //Don't use this! After 2021, I didn't use it anymore. :D
public class CacheConfig {
    public static final HashMap<String, Object> objects = new HashMap<>();
    private final FileConfiguration fileConfiguration;
    public CacheConfig(FileConfiguration fileConfiguration){
        this.fileConfiguration = fileConfiguration;
    }
    public static void clear(){
        objects.clear();
    }
    public Set<Map.Entry<String, Object>> getMapObjects(){
        return objects.entrySet();
    }
    public Object get(String str){
        if(objects.containsKey(str))
            return objects.get(str);
        Object o = fileConfiguration.get(str);
        if(o!=null){
            if(o.getClass() == String.class)
                o = Chat.translate((String) o);
            objects.put(str, o);
            return o;
        }
        return null;
    }
    public void set(String str, Object obj){
        fileConfiguration.set(str, obj);
    }
}
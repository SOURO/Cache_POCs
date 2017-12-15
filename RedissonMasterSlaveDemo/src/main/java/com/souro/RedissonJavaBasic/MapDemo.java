package com.souro.RedissonJavaBasic;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Hello world!
 *
 */
public class MapDemo {
	public static void main(String[] args) {
		Config config = new Config();
		config.useSingleServer().setAddress("http://127.0.0.1:6379");
		RedissonClient client = Redisson.create(config);
		

        RMap<String, String> demo_map =  client.getMap("demoMap");
        
        demo_map.put("FN","Souro");
        demo_map.put("LN","Mukherjee");
        demo_map.put("ORG","Tricon");
        
        Set<String> allKeys = demo_map.readAllKeySet();
        for (String demo_string : allKeys) {
            System.out.println(demo_string);
        }
        
        Collection<String> allValues = demo_map.readAllValues();
        for (String demo_string : allValues) {
            System.out.println(demo_string);
        }
        
        Set<Entry<String, String>> allEntries = demo_map.readAllEntrySet();
        for (Entry<String, String> demo_entry : allEntries) {
            System.out.println(demo_entry.getKey() + " " + demo_entry.getValue());
        }
        
        boolean isNewKey = demo_map.fastPut("Interest", "Math");
        boolean isNewKeyPut = demo_map.fastPutIfAbsent("addons", "additional");
        long removedAmount = demo_map.fastRemove("addons");
        for (Entry<String, String> demo_entry : allEntries) {
            System.out.println(demo_entry.getKey() + " " + demo_entry.getValue());
        }
		
		client.shutdown();
	}
}

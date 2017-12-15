package com.souro.RedissonJavaBasic;

import org.redisson.Redisson;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Hello world!
 *
 */
public class ListDemo {
	public static void main(String[] args) {
		Config config = new Config();
		config.useSingleServer().setAddress("http://127.0.0.1:6379");
		RedissonClient client = Redisson.create(config);
		
		RList<String> demo_list = client.getList("demoList");
		demo_list.add("Souro");
		demo_list.add("Mukherjee");
		demo_list.add("Tricon");
		
		for (String demo_string : demo_list) {
            System.out.println(demo_string);
        }
		
		boolean removedValue = demo_list.remove("Tricon");
		System.out.println("removal status " + removedValue + ", after removal:");
		for (String demo_string : demo_list) {
            System.out.println(demo_string);
        }
		
		client.shutdown();
	}
}

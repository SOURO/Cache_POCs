/**
 * 
 */
package com.souro.SpringDataRedisdemo;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.souro.SpringDataRedisdemo.dao.Research;
import com.souro.SpringDataRedisdemo.service.Service;

/**
 * @author sourabrata
 *
 */
public class Application {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-App-Config.xml");
		Service service = (Service) context.getBean("service");

		Research research1 = new Research();
		research1.setResearch_id(5);
		research1.setResearch_duration(3.5);
		research1.setResearch_field("Machine Learning");
		service.save(research1);

		Research research2 = new Research();
		research2.setResearch_id(3);
		research2.setResearch_duration(5.3);
		research2.setResearch_field("Applied Statictics");
		service.save(research2);

		Research research3 = new Research();
		research3.setResearch_id(53);
		research3.setResearch_duration(5);
		research3.setResearch_field("Mathematics");
		service.save(research3);

		System.out.println("Finding the first stored object : "
				+ service.find(5));
		Map<Object, Object> researchMatrixMap = service.findAll();
		System.out.println("Currently in the Redis Matrix");
		System.out.println(researchMatrixMap);
		System.out.println("Deleting by Research Id ");
		service.delete(53);
		researchMatrixMap = service.findAll();
		System.out.println("After Deletion : ");
		System.out.println(researchMatrixMap);
	}

}

/**
 * 
 */
package com.souro.SpringDataRedisdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.souro.SpringDataRedisdemo.config.AppConfig;
import com.souro.SpringDataRedisdemo.dao.Research;
import com.souro.SpringDataRedisdemo.service.ResearchService;

/**
 * @author sourabrata
 *
 */
public class ApplicationDriver {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		ResearchService rs = ctx.getBean(ResearchService.class);
		Research research = rs.getDetails(5);
		System.out.println("Research Id: "+ research.getResearch_id()+ " Research Field: " +research.getResearch_field()+ " Research Duration: " +research.getResearch_duration());
		research = rs.getDetails(3);
		System.out.println("Research Id: "+ research.getResearch_id()+ " Research Field: " +research.getResearch_field()+ " Research Duration: " +research.getResearch_duration());
		research = rs.getDetails(5);
		System.out.println("Research Id: "+ research.getResearch_id()+ " Research Field: " +research.getResearch_field()+ " Research Duration: " +research.getResearch_duration());
		research = rs.getDetails(3);
		System.out.println("Research Id: "+ research.getResearch_id()+ " Research Field: " +research.getResearch_field()+ " Research Duration: " +research.getResearch_duration());
	}
}

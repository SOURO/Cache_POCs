/**
 * 
 */
package com.souro.service;

import org.springframework.cache.annotation.Cacheable;

import com.souro.dao.ResearchDao;

/**
 * @author sourabrata
 *
 */
public class ResearchService{

	@Cacheable(value="research", condition="#id==5")
	public ResearchDao getDetails(int id){
		ResearchDao research = new ResearchDao();
		research.setResearch_id(id);
		research.setResearch_duration(3.5);
		research.setResearch_field("Machine Learning");
		System.out.println("Value (for Id: "+ id  +") is not getting accessed from Cache");
		return research;
	}
	
/*	@CacheEvict(value="research")
	public void saveDetails(Research research){
		
	}
	
	@CachePut
	public void create(int id){
		
	}*/

}

/**
 * 
 */
package com.souro.service;

import java.util.Map;

import com.souro.dao.ResearchDao;

/**
 * @author sourabrata
 *
 */
public interface ResearchService {

	public void save(ResearchDao research);

	public ResearchDao find(int id);

	public Map<Object, Object> findAll();

	public void delete(int id);
}

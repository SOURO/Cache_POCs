/**
 * 
 */
package com.souro.SpringDataRedisdemo.service;

import java.util.Map;

import com.souro.SpringDataRedisdemo.dao.Research;

/**
 * @author sourabrata
 *
 */
public interface Service {

	public void save(Research research);

	public Research find(int id);

	public Map<Object, Object> findAll();

	public void delete(int id);
}

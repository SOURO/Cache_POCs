/**
 * 
 */
package com.souro.SpringDataRedisdemo.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

/**
 * @author sourabrata
 *
 */
@Repository
public class Research implements Serializable {

	private static final long serialVersionUID = -2399090886094672901L;
	private int research_id;
	private String research_field;
	private double research_duration;

	public int getResearch_id() {
		return research_id;
	}

	public void setResearch_id(int research_id) {
		this.research_id = research_id;
	}

	public String getResearch_field() {
		return research_field;
	}

	public void setResearch_field(String research_field) {
		this.research_field = research_field;
	}

	public double getResearch_duration() {
		return research_duration;
	}

	public void setResearch_duration(double d) {
		this.research_duration = d;
	}
}

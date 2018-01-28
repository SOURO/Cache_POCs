/**
 * 
 */
package com.souro.dao;

import java.io.Serializable;

/**
 * @author sourabrata
 *
 */
public class ResearchDao implements Serializable {
	private static final long serialVersionUID = 8975053469033922985L;
	
	private String research_id;
	private String research_field;
	private double research_duration;

	public String getResearch_id() {
		return research_id;
	}

	public void setResearch_id(String researchId) {
		this.research_id = researchId;
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
	
	@Override
	public String toString(){
		return getResearch_id() + " " + getResearch_field() + " " + getResearch_duration();
	}
}

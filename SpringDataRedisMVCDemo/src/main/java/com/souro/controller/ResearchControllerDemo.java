/**
 * 
 */
package com.souro.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.souro.dao.ResearchDao;
import com.souro.service.ResearchService;

/**
 * @author sourabrata
 *
 */
@Controller
@RequestMapping("/research")
public class ResearchControllerDemo {
	@Autowired
	ResearchService service;

	@RequestMapping(value = "/getDetails", method = RequestMethod.GET)
	public ModelAndView showResearchDetails(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("researchpage");
		ResearchDao research = new ResearchDao();
		research.setResearch_id(5);
		research.setResearch_duration(3.5);
		research.setResearch_field("Machine Learning");
		service.save(research);

		mv.addObject("research", service.find(5).getResearch_field());
		return mv;
	}
}

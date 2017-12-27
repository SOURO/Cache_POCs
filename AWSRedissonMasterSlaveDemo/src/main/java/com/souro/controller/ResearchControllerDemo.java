/**
 * 
 */
package com.souro.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.souro.service.ResearchService;

/**
 * @author sourabrata
 *
 */
@Controller
@RequestMapping("/research")
public class ResearchControllerDemo {
	@Autowired
	ResearchService rs;

	@RequestMapping(value = "/getDetails/{researchId}", method = RequestMethod.GET)
	public ModelAndView showResearchDetails(@PathVariable("researchId") String researchId, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("researchpage");
		mv.addObject("research", rs.getDetails(researchId).toString());
		return mv;
	}
}

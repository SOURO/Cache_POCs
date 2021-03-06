/**
 * 
 */
package com.souro.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.souro.config.AppConfig;
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
		@SuppressWarnings("resource")
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		ResearchService rs = ctx.getBean(ResearchService.class);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("researchpage");
		mv.addObject("research", rs.getDetails(5).getResearch_field());
		return mv;
	}
}

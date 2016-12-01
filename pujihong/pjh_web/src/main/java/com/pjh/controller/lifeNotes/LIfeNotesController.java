package com.pjh.controller.lifeNotes;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="pujihong/daily/lifeNotes")
public class LIfeNotesController {
	//首页
		@RequestMapping(value="")
		public String index(ModelMap map,HttpServletRequest request) {
	        String viewName = "daily/lifeNotes/lifeNotes";
	        return viewName;
	    }
}

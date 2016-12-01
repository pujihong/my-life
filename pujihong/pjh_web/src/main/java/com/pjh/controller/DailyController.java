package com.pjh.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pjh.model.TimeManagement;
import com.pjh.service.TimeManagementService;

@Controller
@RequestMapping(value="pujihong/daily")
public class DailyController {
	@Autowired
	private TimeManagementService timeManagementService;
	//时间首页
	@RequestMapping(value="index")
	public String index(ModelMap map,HttpServletRequest request) {
        String viewName = "daily/index";
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        List<TimeManagement> list = timeManagementService.getTimeMangementTask(userId);
        map.put("task", list);
        return viewName;
    }
	
	//完成计划
	@RequestMapping(value="timeManagement/finishTask",method = RequestMethod.POST)
	@ResponseBody
	public int finishTask(HttpServletRequest request) {
		int taskId = Integer.parseInt(request.getParameter("id"));
		return timeManagementService.finishTask(taskId);
	}
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="timeManagement/editTask",method = RequestMethod.POST)
	@ResponseBody
	public int editTask(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		//这里需要更改下格式，不然无法格式化
		String timeStr = request.getParameter("time").replace("/", "-");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date time = null;
		try {
			time = df.parse(timeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timeManagementService.editTask(id,title,description,time);
	}
	//删除计划
	@RequestMapping(value="timeManagement/deleteTask",method = RequestMethod.POST)
	@ResponseBody
	public int deleteTask(HttpServletRequest request) {
		int taskId = Integer.parseInt(request.getParameter("id"));
		return timeManagementService.deleteTask(taskId);
	}
}

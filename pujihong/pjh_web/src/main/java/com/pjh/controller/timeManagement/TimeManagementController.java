package com.pjh.controller.timeManagement;

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
@RequestMapping(value="pujihong/daily/timeManagement")
public class TimeManagementController {
	@Autowired
	private TimeManagementService timeManagementService;
	//首页
	@RequestMapping(value="")
	public String index(ModelMap map,HttpServletRequest request) {
        String viewName = "daily/timeManagement/timeManagement";
        String today = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        Integer userId =  (Integer) request.getSession().getAttribute("userId");
        //默认查询当天的
        String dateStart = (String) request.getSession().getAttribute("dateStart");
        String dateEnd = (String) request.getSession().getAttribute("dateEnd");
        if(dateStart == null || dateEnd == null) {
        	dateStart = today;
        	dateEnd = today;
        }
        List<TimeManagement> list = timeManagementService.queryTaskByDate(dateStart,dateEnd,userId);
        map.put("task", list);
        map.put("taskSize", list.size());
        return viewName;
    }
	
	
	//新增计划任务
	@RequestMapping(value="timeManagement/addTask",method = RequestMethod.POST)
	@ResponseBody
	public int addTask(HttpServletRequest request) {
		TimeManagement timeManagement = new TimeManagement();
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
		Integer userId = (Integer) request.getSession().getAttribute("userId");
		timeManagement.setTitle(title);
		timeManagement.setDescription(description);
		timeManagement.setTime(time);
		timeManagement.setUserid(userId);
		return timeManagementService.addTask(timeManagement);
	}
	
	//点击查询时把时间保存到session里面
		@RequestMapping(value="timeManagement/saveDateToSession",method = RequestMethod.POST)
		@ResponseBody
		public void queryTaskByDate(ModelMap map,HttpServletRequest request) {
			 String dateStart = request.getParameter("dateStart");
			 String dateEnd = request.getParameter("dateEnd");
			 request.getSession().setAttribute("dateStart", dateStart);
			 request.getSession().setAttribute("dateEnd", dateEnd);
		}
}

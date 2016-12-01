package com.pjh.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjh.dao.TimeManagementMapper;
import com.pjh.model.TimeManagement;

@Service("timeManagementService")
public class TimeManagementServiceImpl implements TimeManagementService{
	private Logger logger = LoggerFactory.getLogger(TimeManagementServiceImpl.class);
	@Autowired
	private TimeManagementMapper timeManagementMapper;
	/**
	 * 查询今日未完成的计划任务
	 */
	@Override
	public List<TimeManagement> getTimeMangementTask(Integer userId) {
		logger.info("查询前3条计划任务");
		List<TimeManagement> list = timeManagementMapper.getTimeMangementTask(userId);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setDatetime(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(list.get(i).getTime()));
		}
		return list;
	}
	/**
     * 通过id 完成计划 update 
     * @param taskId
     * @return
     */
	@Override
	public int finishTask(int taskId) {
		return timeManagementMapper.finishTask(taskId);
	}
	/**
	 * 编辑计划
	 */
	@Override
	public int editTask(int id, String title, String description, Date time) {
		return timeManagementMapper.editTask(id, title, description, time);
	}
    /**
     * 删除计划
     */
	@Override
	public int deleteTask(int taskId) {
		return timeManagementMapper.deleteTask(taskId);
	}
	/**
	 * 根据日期查询计划任务
	 */
	@Override
	public List<TimeManagement> queryTaskByDate(String dateStart,
			String dateEnd, Integer userId) {
		logger.info("根据日期查询计划任务");
		List<TimeManagement> list = timeManagementMapper.queryTaskByDate(dateStart,dateEnd,userId);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setDatetime(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(list.get(i).getTime()));
		}
		return list;
	}
	/**
	 * 新增计划任务
	 */
	@Override
	public int addTask(TimeManagement timeManagement) {
		return timeManagementMapper.addTask(timeManagement);
	}
}	
    

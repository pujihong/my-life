package com.pjh.service;

import java.util.Date;
import java.util.List;

import com.pjh.model.TimeManagement;

public interface TimeManagementService {
   /**
    * 获取时间管理的未完成的前3条任务
    * @param userId 
    */
	public  List<TimeManagement> getTimeMangementTask(Integer userId);
    /**
     * 通过id 完成计划 update 
     * @param taskId
     * @return
     */
    public int finishTask(int taskId);
    /**
     * 编辑任务
     * @param id
     * @param title
     * @param description
     * @param time
     * @return
     */
	public int editTask(int id, String title, String description, Date time);
	/**
	 * 删除计划
	 * @param taskId
	 * @return
	 */
	public int deleteTask(int taskId);
	/**
	 * 根据日期查询任务
	 * @param dateStart
	 * @param dateEnd
	 * @param userId
	 * @return
	 */
	public List<TimeManagement> queryTaskByDate(String dateStart,
			String dateEnd, Integer userId);
	/**
	 * 新增计划任务
	 * @param timeManagement
	 * @return
	 */
	public int addTask(TimeManagement timeManagement); 
}

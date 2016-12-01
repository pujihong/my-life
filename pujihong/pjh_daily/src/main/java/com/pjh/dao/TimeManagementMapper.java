package com.pjh.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pjh.model.TimeManagement;


public interface TimeManagementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TimeManagement record);

    int insertSelective(TimeManagement record);

    TimeManagement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TimeManagement record);

    int updateByPrimaryKey(TimeManagement record);
    /**
     * 查询登录用户今天的前3条数据  如果数据没有3条 则加载模板 模板的id < 0
     * @param username 
     * @return
     */
    @Select("select * from daily_timeManagement where statu = 1 and (date(time) = CURDATE() and userId = #{userId} or id < 0) order by time ASC limit 3")
	public List<TimeManagement> getTimeMangementTask(Integer userId);
    /**
     * 完成任务
     * @param taskId
     * @return
     */
    @Update("update daily_timeManagement set statu = 0 where id = #{taskId} ")
    public int finishTask(int taskId);
    /**
     * 编辑任务
     * @param taskId
     * @return
     */
    @Update("update daily_timeManagement set title = #{1}, description = #{2}, time = #{3} where id = #{0} ")
	public int editTask(int id, String title, String description, Date time);
    /**
     * 删除计划
     * @param taskId
     * @return
     */
    @Delete("delete from daily_timeManagement where id = #{taskId} ")
	public int deleteTask(int taskId);
    /**
     * 根据日期查询计划任务
     * @param dateStart
     * @param dateEnd
     * @param userId
     * @return
     */
    //between '2016-11-01' and '2016-11-03' 结果是1号到2号的数据 所以后面的日期需要加一天
    @Select("select * from daily_timeManagement where time between #{0} and date_add(#{1},interval 1 day) and userId = #{2} order by time DESC")
	List<TimeManagement> queryTaskByDate(String dateStart, String dateEnd,
			Integer userId);
    /**
     * 新增计划任务
     * @param timeManagement
     * @return
     */
    @Insert("insert into daily_timeManagement(time,title,description,userid) values(#{time},#{title},#{description},#{userid})")
	int addTask(TimeManagement timeManagement);
}
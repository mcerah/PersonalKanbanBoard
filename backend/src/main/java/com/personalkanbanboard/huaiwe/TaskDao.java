package com.personalkanbanboard.huaiwe;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TaskDao {
	@Insert("INSERT INTO "
			+ "task(title, description, dueDate, completionDate) "
			+ "VALUES (#{title}, #{description}, #{dueDate}, #{completionDate})")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	int insertTask(Task task);
	
	@Select("SELECT * FROM task")
	List<Task> selectAllTask();

	@Select("SELECT * FROM task WHERE id = #{id}")
	Optional<Task> selectTaskById(long id);

	@Delete("DELETE FROM task WHERE id = #{id}")
	int deleteTaskById(int id);
	
	// title=#{title}, description=#{description}, dueDate=#{dueDate},
	@Update("UPDATE task set status=#{task.status}, completionDate=#{task.completionDate} where id=#{id}")
	int updateTaskById(int id, @Param("task") Task task);
}

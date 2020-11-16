package cn.zsk.shardingconfigyml.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;

/**
 * @author:zsk
 * @CreateTime:2019-10-31 14:30
 */
@TableName("t_user0")
public class UserEntity {
	@TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    @TableLogic(value = "0",delval = "1")
    private String isDeleted;
    private Date createTime;
    private Date lastUpdateTime;

	public Long getId() {
		return id;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}

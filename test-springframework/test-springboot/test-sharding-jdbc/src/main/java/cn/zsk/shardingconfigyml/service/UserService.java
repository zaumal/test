package cn.zsk.shardingconfigyml.service;

import cn.zsk.shardingconfigyml.entity.UserEntity;

import java.util.List;

/**
 * @author:zsk
 * @CreateTime:2019-10-31 14:35
 */
public interface UserService {

    //查询所有记录
    List<UserEntity> getList();

    //新增记录数
    void addUser(UserEntity userEntity);

    //通过id更新记录
    void updateUserById(UserEntity userEntity) throws Exception;

	void deleteUser(Long id);

	List<UserEntity> getDeletedList();

}

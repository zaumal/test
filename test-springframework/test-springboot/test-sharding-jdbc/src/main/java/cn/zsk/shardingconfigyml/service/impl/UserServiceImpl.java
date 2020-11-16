package cn.zsk.shardingconfigyml.service.impl;

import cn.zsk.shardingconfigyml.entity.UserEntity;
import cn.zsk.shardingconfigyml.mapper.UserMapper;
import cn.zsk.shardingconfigyml.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author:zsk
 * @CreateTime:2019-10-31 14:36
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public List<UserEntity> getList() {
        return userMapper.selectList(new QueryWrapper<UserEntity>()
                .eq("is_deleted",0));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserEntity userEntity) {
    	Random r = new Random();
    	int i = r.nextInt(1000);
    	
    	userEntity.setName(userEntity.getName()+i);
    	
    	userEntity.setAge(r.nextInt(60));
    	userEntity.setIsDeleted("0");
    	userEntity.setCreateTime(new Date());
        userMapper.insert(userEntity);
    }

    @Override
    public void updateUserById(UserEntity userEntity) throws Exception {
        if(null == userEntity.getId()){
            throw new Exception("更新用户信息，id为空");
        }
        userMapper.update(null,new UpdateWrapper<UserEntity>()
                .set(null != userEntity.getName(),"name",userEntity.getName())
                .set(null != userEntity.getAge(),"age",userEntity.getAge())
                .set("last_update_time",new Date())
                .eq("id",userEntity.getId()));
    }

	@Override
	public void deleteUser(Long id) {
		userMapper.deleteById(id);
	}

	@Override
	public List<UserEntity> getDeletedList() {
		return userMapper.selectList(new QueryWrapper<UserEntity>()
	                .eq("is_deleted",1));
	}
}

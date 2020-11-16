package cn.zsk.shardingconfigyml.controller;

import cn.zsk.shardingconfigyml.entity.UserEntity;
import cn.zsk.shardingconfigyml.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author:zsk
 * @CreateTime:2019-10-31 14:37
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/deleted")
    public String getDeletedList(){
        List<UserEntity> userEntityList = userService.getDeletedList();
        List<UserEntity> userEntityListTemp = userEntityList.stream().sorted(Comparator.comparing(UserEntity :: getName)).collect(Collectors.toList());
        return JSONObject.toJSONString(userEntityListTemp);
    }
    
    @GetMapping
    public String getList(){
        List<UserEntity> userEntityList = userService.getList();
        List<UserEntity> userEntityListTemp = userEntityList.stream().sorted(Comparator.comparing(UserEntity :: getName)).collect(Collectors.toList());
        return JSONObject.toJSONString(userEntityListTemp);
    }

    @PostMapping
    public void addUser(@RequestBody UserEntity userEntity){
    	userService.addUser(userEntity);

    }

    @PutMapping
    public void updateUser(@RequestBody UserEntity userEntity){
        try {
            userService.updateUserById(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
    	userService.deleteUser(id);
    }
}

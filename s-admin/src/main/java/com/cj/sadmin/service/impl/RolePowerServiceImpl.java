package com.cj.sadmin.service.impl;

import com.cj.sadmin.domain.FindAllRoleResp;
import com.cj.sadmin.domain.UpdateModularByRoleIdReq;
import com.cj.sadmin.service.RolePowerService;
import com.cj.common.domain.AuthModulars;
import com.cj.common.domain.AuthRoleModulars;
import com.cj.common.domain.Modular;
import com.cj.common.entity.AuthRole;
import com.cj.common.exception.UserException;
import com.cj.common.mapper.AuthCustomerRoleMapper;
import com.cj.common.mapper.AuthModularMapper;
import com.cj.common.mapper.AuthRoleMapper;
import com.cj.common.mapper.AuthRoleModularMapper;
import com.cj.common.service.AuthRoleModularService;
import com.cj.core.domain.ApiResult;
import com.cj.core.domain.MemoryData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RolePowerServiceImpl implements RolePowerService {


    @Resource
    private AuthRoleMapper authRoleMapper;

    @Resource
    private AuthModularMapper authModularMapper;

    @Resource
    private AuthCustomerRoleMapper authCustomerRoleMapper;

    @Resource
    private AuthRoleModularMapper authRoleModularMapper;

    @Resource
    private AuthRoleModularService authRoleModularService;


    Gson gson = new Gson();

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public ApiResult findAllRole() {

        //查询所有角色
        List<AuthRole> authRoles = authRoleMapper.findAllAuthRole();
        //获取系统内所有权限
//        AuthModulars authModulars = (AuthModulars) MemoryData.getRoleModularMap().get("authModulars");

        AuthModulars authModulars = gson.fromJson((String) redisTemplate.opsForValue().get("authModulars"),AuthModulars.class);




        FindAllRoleResp findAllRoleResp = new FindAllRoleResp();
        findAllRoleResp.setAuthRoles(authRoles);
        findAllRoleResp.setAuthModulars(authModulars);

        ApiResult apiResult = ApiResult.SUCCESS();
        apiResult.setData(findAllRoleResp);
        return apiResult;
    }

    @Override
    public ApiResult addRole(AuthRole authRole) {
        ApiResult apiResult = null;

        AuthRole oldAuthRole = authRoleMapper.findAuthRoleByName(authRole.getRoleName());
        if (oldAuthRole != null){
            apiResult = ApiResult.FAIL();
            apiResult.setMsg("角色已存在");

            return apiResult;
        }

        int i = authRoleMapper.insertSelective(authRole);
        if (i>0){
            apiResult = ApiResult.SUCCESS();
        }else {
            apiResult = ApiResult.FAIL();
        }
        return apiResult;
    }

    @Override
    public ApiResult deleteRole(Long roleId) {
        ApiResult apiResult = null;

        int i = authCustomerRoleMapper.findCustomerNumByRoleId(roleId);
        if (i>0){
            apiResult = ApiResult.FAIL();
            apiResult.setMsg("此角色已有用户在使用");
            throw new UserException(apiResult);
        }
        //物理删除角色信息
        int j = authRoleMapper.deleteByPrimaryKey(roleId);

        if (j>0){
            apiResult = ApiResult.SUCCESS();
        }else {
            apiResult = ApiResult.FAIL();
        }

        return apiResult;
    }

    //根据角色ID查询权限集合
    @Override
    public ApiResult findModularByRoleId(String json) {
        ApiResult apiResult = null;
        Gson gson = new Gson();
        Map map = gson.fromJson(json,Map.class);
        long roleId = ((Double) map.get("roleId")).longValue();

//        List<AuthRoleModulars> authRoleModulars = (List<AuthRoleModulars>) MemoryData.getRoleModularMap().get("authRoleModulars");
//        AuthModulars authModulars = (AuthModulars) MemoryData.getRoleModularMap().get("authModulars");
        List<AuthRoleModulars> authRoleModulars = gson.fromJson((String)redisTemplate.opsForValue().get("authRoleModulars"),new TypeToken<List<AuthRoleModulars>>(){}.getType());


        for (AuthRoleModulars authRoleModulars0 : authRoleModulars){
            if (roleId == authRoleModulars0.getRoleId()){

                List<Modular> modularIds = authRoleModulars0.getModularIds();
                List<Long> ids = new ArrayList<>();
                for (Modular modular : modularIds){
                    ids.add(modular.getModularId());
                }

                authRoleModulars0.setIds(ids);
                apiResult = ApiResult.SUCCESS();
                apiResult.setData(authRoleModulars0);
                break;

            }else {

                apiResult = ApiResult.FAIL();
            }
        }



        return apiResult;
    }

    @Override
    public ApiResult updateModularByRoleId(UpdateModularByRoleIdReq updateModularByRoleIdReq) {
        ApiResult apiResult = null;
        Gson gson = new Gson();

        long roleId = updateModularByRoleIdReq.getRoleId();
        List<Long> modularIds = updateModularByRoleIdReq.getModularIds();

        Map map = new HashMap();
        map.put("roleId",roleId);
        map.put("modularIds",modularIds);


        int i = authRoleModularMapper.deleteModularByRoleId(roleId);

        int j = authRoleModularMapper.addModularByRoleId(map);

        if (j>0){
            apiResult = ApiResult.SUCCESS();
            //更新系统权限
            authRoleModularService.findRoleModular();

        }else {
            apiResult = ApiResult.FAIL();
        }
        return apiResult;
    }

    @Override
    public ApiResult readRolePower() {
        authRoleModularService.findRoleModular();


        return  ApiResult.SUCCESS();
    }
}

package com.cj.sadmin.service.impl;


import com.cj.common.entity.Admin;
import com.cj.common.entity.AuthCustomerRole;
import com.cj.common.entity.AuthRole;
import com.cj.common.entity.Key64;
import com.cj.core.exception.MyException;
import com.cj.common.mapper.Key64Mapper;
import com.cj.common.service.AuthCustomerRoleService;
import com.cj.common.utils.jwt.JwtUtil;
import com.cj.core.domain.ApiResult;
import com.cj.core.domain.MemoryData;
import com.cj.sadmin.domain.AddAdminResp;
import com.cj.sadmin.domain.IfLoginResp;
import com.cj.sadmin.domain.UpdateAdminByAdminPassReq;
import com.cj.sadmin.mapper.AdminMapper;
import com.cj.sadmin.service.AdminService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.cj.common.utils.md5.Md5Utils.MD5Encode;


@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private Key64Mapper key64Mapper;

    @Resource
    private AuthCustomerRoleService authCustomerRoleService;






    @Resource
    private RedisTemplate redisTemplate;

    //添加账号
    @Override
    public ApiResult addAdmin(Admin admin) {
        ApiResult apiResult;
        //检查账号是否已存在
        Admin oldAdmin = adminMapper.findAdminByName(admin);

        long time = System.currentTimeMillis();

        //账号已存在
        if(oldAdmin != null){
            apiResult = ApiResult.FAIL();
            apiResult.setMsg("账号已存在");
            return apiResult;
        }
        //获取唯一主键
        Key64 key64 = new Key64();
        key64.setStub("a");
        //获取key-adminId
        key64Mapper.addKey64(key64);

        admin.setAdminId(key64.getId());
        //生成盐值
        String uuid = UUID.randomUUID().toString();
        admin.setSaltVal(uuid);
        admin.setAdminType("1");  //系统管理员
        admin.setCreateTime(new Date(time));

        if (admin.getAdminPass() == null || "".equals(admin.getAdminPass())){
            admin.setAdminPass("123456"); //设置初始密码
        }

        //加密密码，MD5（主键+盐值+密码）
        String adminPass = MD5Encode(admin.getAdminId()+admin.getSaltVal()+admin.getAdminPass(),"UTF-8",true);

        admin.setAdminPass(adminPass);
        admin.setAdminType("1");  //设置为管理员

        int i = adminMapper.insertSelective(admin);

        //添加角色
        AuthCustomerRole authCustomerRole = new AuthCustomerRole();
        authCustomerRole.setCustomerId(key64.getId());
        authCustomerRole.setRoleId(admin.getRoleId());
        int k = authCustomerRoleService.addCustomerRole(authCustomerRole);

        Long adminId = admin.getAdminId();
        AddAdminResp addAdminResp = new AddAdminResp();
        addAdminResp.setAdminId(adminId);
        if(i ==1  && k==1){
            apiResult = ApiResult.SUCCESS();
            apiResult.setData(addAdminResp);
            return apiResult;
        }else {
            apiResult = ApiResult.FAIL();
            return apiResult;
        }

    }

    @Override
    public int updateAdmin(Admin admin) {
        ApiResult apiResult;
        Admin oldAdmin = adminMapper.findAdminByName(admin);
        String newAdminPass = MD5Encode(oldAdmin.getAdminId()+oldAdmin.getSaltVal()+admin.getAdminPass(),"UTF-8",true);
        admin.setAdminPass(newAdminPass);
        int i = adminMapper.updateAdminPass(admin);

        return i;
    }

    @Override
    public int delete(Long adminId) {
        return adminMapper.deleteAdmin(adminId);
    }

    @Override
    public List<Admin> findAllAdmin() {
        return adminMapper.findAllAdmin();
    }


    //登录
    @Override
    public ApiResult ifLogin(Admin admin, HttpServletRequest request) {
        ApiResult apiResult = null;

        Admin oldAdmin = adminMapper.findAdminByUserName(admin);

        long time = System.currentTimeMillis();

        if(oldAdmin == null){

            apiResult = ApiResult.FAIL();
            apiResult.setMsg("账号不存在");
            apiResult.setParams(request.getRequestURL());
            throw new MyException(apiResult);


        }else if(oldAdmin.getAdminPass().equals(MD5Encode(oldAdmin.getAdminId()+oldAdmin.getSaltVal()+admin.getAdminPass(),"UTF-8",true))){  //密码正确
            String token = "";

            Long adminId = oldAdmin.getAdminId();
            String adminName = oldAdmin.getAdminName();


            //查询账号角色信息
            List<AuthRole> roles = authCustomerRoleService.findCustomerRoleById(adminId);



            //设置token，有效期
            token = JwtUtil.getToken(adminId,adminName,oldAdmin.getAdminType(),roles);

            String tokenKey = "token:"+adminId;

            Boolean b = redisTemplate.hasKey(tokenKey);
            if (!b){
                redisTemplate.opsForValue().set(tokenKey,token,1800l);
            }else {

                redisTemplate.opsForValue().getAndSet(tokenKey,token);
            }


            IfLoginResp ifLoginResp = new IfLoginResp();
            ifLoginResp.setToken(token);
            ifLoginResp.setIssuedAt(new Date(time));
            ifLoginResp.setRoles(roles);

            apiResult = ApiResult.SUCCESS();
            apiResult.setData(ifLoginResp);
            System.out.println(apiResult);
            return apiResult;
        }else {

            apiResult = ApiResult.FAIL();
            apiResult.setMsg("账号不存在或密码错误");
            return  apiResult;

        }


    }

    @Override
    public IfLoginResp ipLogin(Admin admin, HttpServletRequest request) {

        ApiResult apiResult = new ApiResult();


        Admin oldAdmin = adminMapper.findAdminByUserName(admin);

        long time = System.currentTimeMillis();

        if (oldAdmin == null) {
            apiResult = ApiResult.FAIL();
            apiResult.setMsg("用户:"+admin.getAdminName()+" 未注册到环境风险管理平台，请联系管理人员");
            throw new MyException(apiResult);

        } else {  //不验证密码，
            String token = "";

            Long adminId = oldAdmin.getAdminId();
            String adminName = oldAdmin.getAdminName();

            String tokenKey = adminId.toString();

            //查询账号角色信息
            List<AuthRole> roles = authCustomerRoleService.findCustomerRoleById(adminId);


            //设置token，有效期
            token = JwtUtil.getToken(adminId, adminName, oldAdmin.getAdminType(), roles);




            IfLoginResp ifLoginResp = new IfLoginResp();
            ifLoginResp.setToken(token);
            ifLoginResp.setIssuedAt(new Date(time));
            ifLoginResp.setRoles(roles);

            return ifLoginResp;
        }
    }

    //注销
    @Override
    public int ifLogout(HttpSession session) {
        Integer adminId = (Integer) session.getAttribute("id");
        String tokenKey = adminId.toString();
        if(MemoryData.getTokenMap().containsKey(tokenKey)){
            MemoryData.getTokenMap().remove(tokenKey);  //删除adminId-token
        }
        return 1;
    }

    //修改密码，校验原密码
    @Override
    public ApiResult updateAdminByAdminPass(HttpSession session, UpdateAdminByAdminPassReq updateAdminByAdminPassReq) {


        String oldAdminPass = updateAdminByAdminPassReq.getOldAdminPass();
        String newAdminPass = updateAdminByAdminPassReq.getNewAdminPass();


        ApiResult apiResult;
        Admin admin = new Admin();
        admin.setAdminName((String) session.getAttribute("name"));
        Admin oldAdmin = adminMapper.findAdminByName(admin);

        int i = 0;
        if(MD5Encode(oldAdmin.getAdminId()+oldAdmin.getSaltVal()+oldAdminPass,"UTF-8",true).equals(oldAdmin.getAdminPass())){

            String md5Pass = MD5Encode(oldAdmin.getAdminId()+oldAdmin.getSaltVal()+newAdminPass,"UTF-8",true);
            oldAdmin.setAdminPass(md5Pass);

            admin.setAdminId(oldAdmin.getAdminId());
            admin.setAdminPass(md5Pass);
            i = adminMapper.updateAdminPass(admin);

            if (i>0){
                apiResult = ApiResult.SUCCESS();
            }else {
                apiResult = ApiResult.FAIL();
            }
        }else {

            apiResult = ApiResult.FAIL();
            apiResult.setMsg("密码错误");
        }




        return apiResult;
    }


}
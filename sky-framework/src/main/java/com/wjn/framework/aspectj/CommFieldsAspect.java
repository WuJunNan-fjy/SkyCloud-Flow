package com.wjn.framework.aspectj;

import com.wjn.common.core.domain.BaseEntity;
import com.wjn.common.core.domain.entity.SysUser;
import com.wjn.common.utils.DateUtils;
import com.wjn.common.utils.ServletUtils;
import com.wjn.common.utils.spring.SpringUtils;
import com.wjn.framework.web.service.TokenService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/*
 * 通用字段赋值
 */
@Aspect
@Component
public class CommFieldsAspect {

    // 配置织入点
    @Pointcut("@annotation(com.wjn.common.annotation.CommFields)")
    public void commFieldsPointCut()
    {
    }

    @Before("commFieldsPointCut()")
    public void doBefore(JoinPoint point) throws Throwable
    {
        SysUser currentUser  = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest()).getUser();
        handleCommFields(point,currentUser);
    }

    //为通字段赋值,涉及到一人多岗的情况，去掉注解单独赋值
    protected void handleCommFields(final JoinPoint joinPoint,SysUser user) throws InstantiationException, IllegalAccessException {

        //获取参数
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            String methodName = joinPoint.getSignature().getName();
            //System.out.println("注入的方法名称是："+methodName);
            if(arg instanceof BaseEntity) {
                BaseEntity baseentity = (BaseEntity) arg;
                if(methodName.contains("add")){
                    baseentity.setCreateBy(user.getNickName());
                    baseentity.setCreateTime(DateUtils.getNowDate());
                    baseentity.setUserId(user.getUserId());
                    baseentity.setDeptId(user.getDeptId());
                }else if(methodName.contains("edit")){
                    baseentity.setUpdateBy(user.getNickName());
                    baseentity.setUpdateTime(DateUtils.getNowDate());
                }
            }
        }
    }
}

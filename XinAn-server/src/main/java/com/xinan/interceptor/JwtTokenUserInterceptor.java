package com.xinan.interceptor;

import com.xinan.constant.JwtClaimsConstant;
import com.xinan.context.BaseContext;
import com.xinan.properties.JwtProperties;
import com.xinan.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt令牌校验的拦截器 拦截器在DispatcherServlet之后
 */
@Component
@Slf4j
public class JwtTokenUserInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     *
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器对象
     * @return 是否放行 true放行 false不放行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        System.out.println("当前线程的ID："+Thread.currentThread().getId());

        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getUserTokenName());

        //2、校验令牌
        try {
            log.info("jwt校验:{}", token);
            //解析token，同时也执行了校验功能
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
            //获取创建token所依据的用户信息
            Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
            log.info("当前用户id：{}", userId);
            //这里是拦截器类，每一次请求时都要到达这里，所以在这里获取该线程操作者的ID
            //基于ThreadLocal类，为该线程在内存上分出一块空间，用于存储值，便于后面同个线程调用
            BaseContext.setCurrentId(userId);
            //3、通过，放行
            return true;
        } catch (Exception ex) {
            //4、不通过，响应401状态码
            log.error("令牌解析错误！错误: "+ex.getMessage());
            response.setStatus(401);
            return false;
        }
    }

    /**
     * 视图渲染结束 也就是该次http请求响应之后要删除ThreadLocal 避免内存溢出
     * @param request 请求
     * @param response 响应
     * @param handler 处理器
     * @param ex 异常
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
        log.info("当前线程结束:{}",BaseContext.getCurrentId());
        BaseContext.removeCurrentId();
    }
}

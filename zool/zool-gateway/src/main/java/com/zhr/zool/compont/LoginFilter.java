package com.zhr.zool.compont;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author zhr_java@163.com
 * @date 2022/5/15 21:27
 *
 * 登录校验，某些接口需要添加token才能通过过滤器
 */
@Component
public class LoginFilter extends ZuulFilter {

  /**
   * 过滤器类型，前置过滤器
   *
   * @return
   */
  @Override
  public String filterType() {

        return PRE_TYPE;
    }

    /**
     * 设置当前过滤器的级别,值越小，则优先使用当前过滤器
     * @return
     */
    @Override
    public int filterOrder() {

        return 4;
    }

    /**
     * 过滤是否生效，返回true代表需要权限校验，false代表不需要用户校验即可访问
     * @return
     */
    @Override
    public boolean shouldFilter() {

        //共享RequestContext，上下文对象
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        System.out.println(request.getRequestURI());
        //需要权限校验URL
        if ("/apigateway/order/api/v1/order/save".equalsIgnoreCase(request.getRequestURI())) {
            return true;
        } else if ("/apigateway/order/api/v1/order/list".equalsIgnoreCase(request.getRequestURI())) {
            return true;
        } else if ("/apigateway/order/api/v1/order/find".equalsIgnoreCase(request.getRequestURI())) {
            return true;
        }
        return false;
    }

    /**
     *只有shouldFilter返回true才会执行当前方法
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {

        //JWT
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        //token对象,有可能在请求头传递过来，也有可能是通过参数传过来，实际开发一般都是请求头方式
        String token = request.getHeader("token");

        if (StringUtils.isBlank((token))) {
            token = request.getParameter("token");
        }
        System.out.println("页面传来的token值为：" + token);
        //登录校验逻辑  如果token为null，则直接返回客户端，而不进行下一步接口调用
        if (StringUtils.isBlank(token)) {
            // 过滤该请求，不对其进行路由
            requestContext.setSendZuulResponse(false);
            //返回错误代码
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }

        return null;
    }

}

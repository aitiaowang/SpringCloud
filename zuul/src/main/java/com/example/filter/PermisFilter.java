package com.example.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义zuul请求过滤
 * 请求过滤有点类似于Java中Filter过滤器，先将所有的请求拦截下来，然后根据现场情况做出不同的处理
 */
public class PermisFilter extends ZuulFilter {
    /**
     * filterType方法的返回值为过滤器的类型，过滤器的类型决定了过滤器在哪个生命周期执行，
     * pre表示在路由之前执行过滤器，其他可选值还有post、error、route和static，当然也可以自定义。
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder方法表示过滤器的执行顺序，当过滤器很多时，这个方法会有意义。
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * shouldFilter方法用来判断过滤器是否执行，true表示执行，false表示不执行，
     * 在实际开发中，我们可以根据当前请求地址来决定要不要对该地址进行过滤，这里我直接返回true
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 表示过滤的具体逻辑，假设请求地址中携带了login参数的话，则认为是合法请求，否则就是非法请求，
     * 如果是非法请求的话，首先设置ctx.setSendZuulResponse(false);表示不对该请求进行路由，然后设置响应码和响应值。
     * 这个run方法的返回值在当前版本(Dalston.SR3)中暂时没有任何意义，可以返回任意值。
     * @return
     */
    @Override
    public Object run() {
        //得到当前上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String login = request.getParameter("login");
        if (login == null) {
            //不对该请求进行路由
            ctx.setSendZuulResponse(false);
            //未授权
            ctx.setResponseStatusCode(401);
            //设置响应码和响应值
            ctx.addZuulResponseHeader("content-type","text/html;charset=utf-8");
            ctx.setResponseBody("非法访问");
        }
        return null;
    }
}

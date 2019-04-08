package com.jxbig.sharp.servicezuul.common.config.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class MyFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(MyFilter.class);

    /**
     * filterType
     * pre: 路由之前
     * routing: 路由时
     * post: 路由后
     * error: 错误时调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * true：永久过滤
     * false：不进行过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体实现，可sql, nosql，查询权限等
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        logger.info(String.format("%s >>>> %s", request.getMethod(), request.getRequestURL().toString()));
        String token = request.getParameter("token");
        if (token == null) {
            logger.warn("token is empty");
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            try {
                currentContext.getResponse().getWriter().write("token is empty");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("ok");
        return null;
    }
}

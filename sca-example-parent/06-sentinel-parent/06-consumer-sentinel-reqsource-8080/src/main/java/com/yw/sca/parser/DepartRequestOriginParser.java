package com.yw.sca.parser;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义原始请求解析器，其用于从请求中获取 来源标识
 * @author yangwei
 * @date 2020-12-27 22:32
 */
@Component
public class DepartRequestOriginParser implements RequestOriginParser {
    /**
     * 该方法的返回值即为请求来源标识
     */
    @Override
    public String parseOrigin(HttpServletRequest request) {
        String source = request.getParameter("source");
        if (StringUtils.isEmpty(source)) {
            return "serviceA";
        }
        // 返回的就是来源标识
        return source;
    }
}

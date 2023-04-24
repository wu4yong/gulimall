package com.atguigu.gulimall.search.config;


import org.springframework.context.annotation.Configuration;

/**
 *
 * @author wuyong
 * @email wu4yong@163.com
 * @date 2023-04-02 12:29:36
 */
@Configuration
public class GulimallSearchSentinelConfig {

    public GulimallSearchSentinelConfig() {

//        WebCallbackManager.setUrlBlockHandler(new UrlBlockHandler() {
//            @Override
//            public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException ex) throws IOException {
//                R error = R.error(BizCodeEnum.TO_MANY_REQUEST.getCode(), BizCodeEnum.TO_MANY_REQUEST.getMessage());
//                response.setCharacterEncoding("UTF-8");
//                response.setContentType("application/json");
//                response.getWriter().write(JSON.toJSONString(error));
//
//            }
//        });

    }

}

package com.example.demo.interceptor;
import com.example.demo.domain.AccessLimit;
import com.example.demo.domain.Detail;
import com.example.demo.utils.IpUtils;
import com.example.demo.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class AccessInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    IpUtils ipUtils;
    @Autowired
    Detail detail;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            //请求的方法是否带有accesslimit注解
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if (accessLimit == null) {
                return false;
            }
            int seconds = accessLimit.seconds();
            int maxCound = accessLimit.maxCount();
            boolean needLogin = accessLimit.needLogin();
            String key = request.getRequestURI();
            //判断需不需要登录才能进行的操作
            if(needLogin) {
                //如果没有登录，不让访问
//                if(user == null) {
//                    //render(response, CodeMsg.SESSION_ERROR);
//                    return false;
//                }
            }else {
                //do nothing
            }
            //查询该IP请求的次数是否超数
            if(ipUtils.redisIP(request)){
                System.out.println("一切正常！！！");
                return true;
            }else{
                detail.setState(403);
                detail.setMessage("拒绝访问-访问次数超过");
                reder(response, request, detail);
                return false;
            }

//            String loginStr = (String) request.getSession().getAttribute("login");
//            HttpSession session = request.getSession();
//            Integer count = (Integer) session.getAttribute("count");
//            if (count == null) {
//                session.setAttribute("count", 1);
//            } else if (count < maxCound) {
//                count++;
//                session.setAttribute("count", count);
//            } else {
//                reder(response, request, "超过了数据");
//                return false;
//            }
        }
        return true;
    }
    private void reder(HttpServletResponse response, HttpServletRequest request, Detail detail) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        out.write(JsonUtils.objectToJson(detail).getBytes());
        out.flush();
        out.close();
    }
}
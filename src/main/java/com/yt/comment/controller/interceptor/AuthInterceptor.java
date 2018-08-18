package com.yt.comment.controller.interceptor;

import com.yt.comment.util.CommonUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Description:权限拦截器
 *
 * @author:Tong
 */
public class AuthInterceptor implements HandlerInterceptor {

    /**
     * 在进入Handler方法执行之前执行本方法
     * @param request
     * @param response
     * @param o
     * @return true:执行下一个拦截器，知道所有拦截器都执行完，再执行被拦截的Controller
     *          false：从当前拦截器往回执行所有拦截器的afterCompletion（），再退出拦截器
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        if (CommonUtil.containsUrl(request.getSession(),request.getServletPath(),request.getMethod())) {
            return true;
        } else {
            if (!CommonUtil.isEmpty(request.getHeader("X-Requestes-With"))) {
                String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
                response.setHeader("url",basePath + "/login/noAuth");
            } else {
                request.getRequestDispatcher("/login/noAuth").forward(request,response);
            }
            return false;
        }
    }

    /**
     * 在进入Handler方法之后，返回ModelAndView之前执行
     * @param request
     * @param response
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在Handler方法执行完成后执行
     * @param request
     * @param response
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
}

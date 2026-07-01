package net.mohamadi.App.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.mohamadi.Service.user.UserService;
import net.mohamadi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class JwtFilter implements Filter {

    private final JwtUtil jwtUtil;
    public static final String CURRENT_USER = "CURRENT_USER";
    private final UserService userService;

    @Autowired
    public JwtFilter(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }


    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = extractTokenFromHeader(httpRequest);

        //قطعه کد زیر دیگر به درد نمیخورد چون همه ی
        // درخواست های کاربران از فیلتر عبور میکنند و
        // هربار باید مقایسه انجام شود که باعث سربار میشود.
        // اما در عوض کلاس FilterConfig را نوشتیم که دیگر سربار مقایسه کردن را نداشته باشیم !!

//        String url = ((HttpServletRequest) request).getRequestURL().toString();
//        if (!url.contains("/api/panel/")) {
//            chain.doFilter(request, response);
//            return;
//        }


        if (!token.isEmpty() && jwtUtil.validateToken(token)) {
            String username = jwtUtil.getUserNameFromJWT(token);
            try {
                httpRequest.setAttribute(CURRENT_USER, userService.readUserByUserName(username));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            chain.doFilter(request, response);
        } else
            httpResponse.getWriter().write("Access Denied in JWT Filter");

    }

    private static String extractTokenFromHeader(HttpServletRequest httpRequest) {
        String bearerToken = httpRequest.getHeader("Authorization");
        String prefix = "Bearer";
        String token = "";
        if (bearerToken != null && bearerToken.startsWith(prefix)) {
            token = bearerToken.substring(prefix.length());
        }
        return token;
    }
}

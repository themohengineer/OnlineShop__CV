package net.mohamadi.App.aspect;


import jakarta.servlet.http.HttpServletRequest;

import net.mohamadi.App.annotation.CheckPermission;
import net.mohamadi.App.filter.JwtFilter;
import net.mohamadi.App.model.APIResponse;
import net.mohamadi.dto.user.PermissionDto;
import net.mohamadi.dto.user.UserDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class PermissionAspect {


    private final HttpServletRequest request;


    public PermissionAspect(HttpServletRequest request) {
        this.request = request;
    }


    @Around("@annotation(checkPermission)")
    public Object checkUserPermission(
            ProceedingJoinPoint joinPoint,
            CheckPermission checkPermission) throws Throwable {
        UserDto user = (UserDto) request.getAttribute(JwtFilter.CURRENT_USER);
        if (user == null) {
            return APIResponse
                    .builder()
                    .message("Please Login First !")
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }

        //این کد یک لیست از نام دسترسی‌ها (Permission) را از کاربر جاری استخراج می‌کند.
        List<String> permission = user
                .getRoles()
                .stream().
                flatMap(role -> role.getPermissions()
                        .stream()
                        .map(PermissionDto::getName))
                .toList();
        if (!permission.contains(checkPermission.value())) {
            return APIResponse
                    .builder()
                    .status(HttpStatus.FORBIDDEN)
                    .message("Access Denied !")
                    .build();
        }
        return joinPoint.proceed();
    }


}

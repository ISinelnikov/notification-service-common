package notification.service.utils;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;

public final class RequestUtils {
    private RequestUtils() {
    }

    public static String getSourceIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Real-IP");
        return forwarded == null ? request.getRemoteAddr() : forwarded;
    }

    @Nullable
    public static String getSecurityToken(HttpServletRequest request) {
        return request.getHeader("Access-Token");
    }
}


package notification.service.domain;

import java.util.Objects;

public class ApiResponse {
    private final boolean success;
    private final String message;

    private ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = Objects.requireNonNull(message, "Message can't be null.");
    }

    public static ApiResponse getSuccessResponse(String message) {
        return new ApiResponse(true, message);
    }

    public static ApiResponse getFailedResponse(String message) {
        return new ApiResponse(false, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}

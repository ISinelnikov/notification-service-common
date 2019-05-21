package notification.service.domain.notification.rich.component;

import notification.service.domain.notification.rich.RichNotification;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RichWrapper {
    private final String userId;
    private final String templateId;
    private final RichNotification notification;

    @JsonCreator
    public RichWrapper(@JsonProperty("userId") String userId,
            @JsonProperty("templateId") String templateId,
            @JsonProperty("notification") RichNotification notification) {
        this.userId = Objects.requireNonNull(userId, "User id can't be null.");
        this.templateId = Objects.requireNonNull(templateId, "Template id can't be null.");
        this.notification = Objects.requireNonNull(notification,
                "Firebase notification can't be null.");
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getUserId() {
        return userId;
    }

    public RichNotification getNotification() {
        return notification;
    }

    @Override
    public String toString() {
        return "RichWrapper{" +
                "userId='" + userId + '\'' +
                ", templateId='" + templateId + '\'' +
                ", notification=" + notification +
                '}';
    }
}

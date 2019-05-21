package notification.service.domain.notification.rich.component;

import notification.service.domain.notification.rich.RichNotification;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RichWrapper {
    private final String userId;
    private final String templateId;
    private final RichNotification firebaseNotification;

    @JsonCreator
    public RichWrapper(@JsonProperty("firebaseKey") String firebaseKey,
            @JsonProperty("userId") String userId,
            @JsonProperty("templateId") String templateId,
            @JsonProperty("firebaseNotification") RichNotification firebaseNotification) {
        this.userId = Objects.requireNonNull(userId, "User id can't be null.");
        this.templateId = Objects.requireNonNull(templateId, "Template id can't be null.");
        this.firebaseNotification = Objects.requireNonNull(firebaseNotification,
                "Firebase notification can't be null.");
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getUserId() {
        return userId;
    }

    public RichNotification getFirebaseNotification() {
        return firebaseNotification;
    }

    @Override
    public String toString() {
        return "RichWrapper{" +
                ", userId='" + userId + '\'' +
                ", templateId='" + templateId + '\'' +
                ", firebaseNotification=" + firebaseNotification +
                '}';
    }
}

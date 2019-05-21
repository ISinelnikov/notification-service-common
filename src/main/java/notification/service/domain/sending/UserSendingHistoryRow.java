package notification.service.domain.sending;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.annotation.Nullable;

public class UserSendingHistoryRow {
    private static final String NA = "N/A";

    private final String userId;
    private final String templateId;
    @Nullable
    private final String messageId;
    private final LocalDateTime sendingTime;
    private final SendingStatus sendingStatus;

    public UserSendingHistoryRow(String userId, String templateId, @Nullable String messageId, LocalDateTime sendingTime,
            SendingStatus sendingStatus) {
        this.userId = Objects.requireNonNull(userId, "User id can't be null.");
        this.templateId = Objects.requireNonNull(templateId, "Template id can't be null.");
        this.messageId = messageId;
        this.sendingTime = Objects.requireNonNull(sendingTime, "Sending time can't be null.");
        this.sendingStatus = Objects.requireNonNull(sendingStatus, "Sending status can't be null.");
    }

    public String getUserId() {
        return userId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getMessageId() {
        return messageId == null ? NA : messageId;
    }

    public LocalDateTime getSendingTime() {
        return sendingTime;
    }

    public SendingStatus getSendingStatus() {
        return sendingStatus;
    }

    @Override
    public String toString() {
        return "UserSendingInfo{" +
                "userId='" + userId + '\'' +
                ", templateId='" + templateId + '\'' +
                ", messageId='" + messageId + '\'' +
                ", sendingTime=" + sendingTime +
                ", sendingStatus=" + sendingStatus +
                '}';
    }
}

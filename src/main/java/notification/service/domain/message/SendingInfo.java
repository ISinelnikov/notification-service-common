package notification.service.domain.message;

import java.util.Objects;
import javax.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendingInfo {
    private final String userId;
    @Nullable
    private final String messageId;
    private final String templateId;

    @JsonCreator
    public SendingInfo(@JsonProperty("userId") String userId,
            @JsonProperty("templateId") String templateId,
            @JsonProperty("messageId") @Nullable String messageId) {
        this.userId = Objects.requireNonNull(userId, "User id can't be null.");
        this.templateId = Objects.requireNonNull(templateId, "Template id can't be null.");
        this.messageId = messageId;
    }

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("templateId")
    public String getTemplateId() {
        return templateId;
    }

    @Nullable
    @JsonProperty("messageId")
    public String getMessageId() {
        return messageId;
    }

    @Override
    public String toString() {
        return "SendingInfo{" +
                "userId='" + userId + '\'' +
                ", messageId='" + messageId + '\'' +
                ", templateId='" + templateId + '\'' +
                '}';
    }
}

package notification.service.domain.message;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageId {
    private final String messageId;

    @JsonCreator
    public MessageId(@JsonProperty("messageId") String messageId) {
        this.messageId = Objects.requireNonNull(messageId, "Message id can't be null.");
    }

    public String getMessageId() {
        return messageId;
    }

    @Override
    public String toString() {
        return "MessageId{" +
                "messageId='" + messageId + '\'' +
                '}';
    }
}

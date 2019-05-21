package notification.service.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteIdDto {
    private final String userId;

    @JsonCreator
    public DeleteIdDto(@JsonProperty("userId") String userId) {
        this.userId = Objects.requireNonNull(userId, "User id can't be null.");
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "DeleteIdDto{" +
                "userId='" + userId + '\'' +
                '}';
    }
}

package notification.service.domain;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NodeRegistrationDto implements Serializable {
    private static final long serialVersionUID = 7407323152624059174L;

    private final String userId;
    private final String emailAddress;

    @JsonCreator
    public NodeRegistrationDto(@JsonProperty("userId") String userId,
            @JsonProperty("emailAddress") String emailAddress) {
        this.userId = Objects.requireNonNull(userId, "User id can't be null");
        this.emailAddress = Objects.requireNonNull(emailAddress, "Email address can't be null.");
    }

    public String getUserId() {
        return userId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "NodeRegistrationDto{" +
                "userId='" + userId + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}

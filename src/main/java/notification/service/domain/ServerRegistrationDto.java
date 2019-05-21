package notification.service.domain;

import java.io.Serializable;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ServerRegistrationDto implements Serializable {
    private static final long serialVersionUID = 3550368031594186660L;

    private final NodeRegistrationDto nodeRegistrationDto;
    private final String ipAddress;

    @JsonCreator
    public ServerRegistrationDto(@JsonProperty("userId") String userId,
            @JsonProperty("emailAddress") String emailAddress,
            @JsonProperty("ipAddress") String ipAddress) {
        this.nodeRegistrationDto = new NodeRegistrationDto(userId, emailAddress);
        this.ipAddress = Objects.requireNonNull(ipAddress, "Ip address id can't be null");
    }

    public ServerRegistrationDto(NodeRegistrationDto nodeRegistrationDto,
            String ipAddress) {
        this(nodeRegistrationDto.getUserId(), nodeRegistrationDto.getEmailAddress(), ipAddress);
    }

    public String getUserId() {
        return nodeRegistrationDto.getUserId();
    }

    public String getNotificationId() {
        return nodeRegistrationDto.getEmailAddress();
    }

    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public String toString() {
        return "ServerRegistrationDto{" +
                "nodeRegistrationDto=" + nodeRegistrationDto +
                ", ipAddress='" + ipAddress + '\'' +
                '}';
    }
}


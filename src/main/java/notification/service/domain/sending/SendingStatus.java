package notification.service.domain.sending;

import java.util.Objects;
import java.util.stream.Stream;

public enum SendingStatus {
    DELIVERED("D", "Delivered"),
    SENDING("S", "Sending"),
    ERROR("E", "Error");

    private final String alias;
    private final String description;

    SendingStatus(String alias, String description) {
        this.alias = Objects.requireNonNull(alias, "Alias can't be null.");
        this.description = Objects.requireNonNull(description, "Description can't be null.");
    }

    public String getAlias() {
        return alias;
    }

    public String getDescription() {
        return description;
    }

    public static SendingStatus findSendingStatusByAlias(String alias) {
        return Stream.of(values())
                .filter(sendingStatus -> sendingStatus.getAlias().equals(alias))
                .findFirst()
                .orElse(ERROR);
    }
}

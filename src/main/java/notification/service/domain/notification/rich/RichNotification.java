package notification.service.domain.notification.rich;

import notification.service.domain.notification.rich.RichTemplate;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RichNotification {
    private final String to;
    private final RichTemplate data;

    @JsonCreator
    public RichNotification(@JsonProperty("to") String to,
            @JsonProperty("data") RichTemplate data) {
        this.to = Objects.requireNonNull(to, "To (destination) can't be null.");
        this.data = Objects.requireNonNull(data, "Data can't be null.");
    }

    @JsonProperty("to")
    public String getTo() {
        return to;
    }

    @JsonProperty("data")
    public RichTemplate getTemplate() {
        return data;
    }

    @Override
    public String toString() {
        return "RichNotification{" +
                "to='" + to + '\'' +
                ", data=" + data +
                '}';
    }
}

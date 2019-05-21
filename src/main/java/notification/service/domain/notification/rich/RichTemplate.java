package notification.service.domain.notification.rich;

import notification.service.utils.JsonUtils;

import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RichTemplate {

    // Common fields
    private final String bodyHref;
    @Nullable
    private final String bodyImageLink;
    private final boolean ringtoneOn;
    @Nullable
    private final List<Integer> vibration;

    // Notification bodies
    @Nullable
    private final RichSmallNotification smallNotification;
    @Nullable
    private final RichBigNotification bigNotification;

    @JsonCreator
    public RichTemplate(@JsonProperty("href") String bodyHref,
            @JsonProperty("top_image") @Nullable String topImageLink,
            @JsonProperty("ringtone_on") boolean ringtoneOn,
            @JsonProperty("vibration") @Nullable List<Integer> vibration,
            @JsonProperty("small_notification") @Nullable RichSmallNotification smallNotification,
            @JsonProperty("big_notification") @Nullable RichBigNotification bigNotification) {
        this.bodyHref = Objects.requireNonNull(bodyHref, "Body href can't be null.");
        this.ringtoneOn = ringtoneOn;
        this.bodyImageLink = topImageLink;
        this.vibration = vibration;
        this.smallNotification = smallNotification;
        this.bigNotification = bigNotification;
    }

    @JsonProperty("href")
    public String getBodyHref() {
        return bodyHref;
    }

    @Nullable
    @JsonProperty("top_image")
    public String getBodyImageLink() {
        return JsonUtils.getStringTrimOrNull(bodyImageLink);
    }

    @JsonProperty("ringtone_on")
    public boolean isRingtoneOn() {
        return ringtoneOn;
    }

    @Nullable
    @JsonProperty("vibration")
    public List<Integer> getVibration() {
        return vibration;
    }

    @Nullable
    @JsonProperty("small_notification")
    public RichSmallNotification getSmallNotification() {
        return smallNotification;
    }

    @Nullable
    @JsonProperty("big_notification")
    public RichBigNotification getBigNotification() {
        return bigNotification;
    }

    @Override
    public String toString() {
        return "RichTemplate{" +
                "bodyHref='" + bodyHref + '\'' +
                ", bodyImageLink='" + bodyImageLink + '\'' +
                ", ringtoneOn=" + ringtoneOn +
                ", vibration=" + vibration +
                ", smallNotification=" + smallNotification +
                ", bigNotification=" + bigNotification +
                '}';
    }
}

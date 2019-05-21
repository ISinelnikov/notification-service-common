package notification.service.domain.notification.rich;

import notification.service.domain.notification.rich.component.RichTextElement;

import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseRichNotification {
    protected final RichTextElement application;
    @Nullable
    protected final RichTextElement title;
    @Nullable
    private final String image;
    protected final String icon;
    protected final List<RichTextElement> richTextElements;

    public BaseRichNotification(@JsonProperty("app") RichTextElement application,
            @JsonProperty("title") @Nullable RichTextElement title,
            @JsonProperty("icon") String icon,
            @JsonProperty("image") @Nullable String image,
            @JsonProperty("body") @Nullable List<RichTextElement> richTextElements) {
        this.application = Objects.requireNonNull(application, "Application can't be null.");
        this.icon = Objects.requireNonNull(icon, "Icon can't be null.");
        this.title = title;
        this.image = image;
        this.richTextElements = richTextElements;
    }

    @JsonProperty("app")
    public RichTextElement getApplication() {
        return application;
    }

    @Nullable
    @JsonProperty("title")
    public RichTextElement getTitle() {
        return title;
    }

    @JsonProperty("icon")
    public String getIcon() {
        return icon;
    }

    @Nullable
    @JsonProperty("body")
    public List<RichTextElement> getRichTextElements() {
        return richTextElements;
    }

    @Nullable
    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "BaseRichNotification{" +
                "application='" + application + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", icon='" + icon + '\'' +
                ", richTextElements=" + richTextElements +
                '}';
    }
}

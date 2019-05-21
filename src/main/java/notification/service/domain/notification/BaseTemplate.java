package notification.service.domain.notification;

import notification.service.domain.notification.rich.component.RichTextElement;
import notification.service.utils.JsonUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;

public class BaseTemplate {
    @Nullable
    protected RichTextElement application;
    @Nullable
    protected RichTextElement title;
    @Nullable
    protected List<RichTextElement> body;
    @Nullable
    protected String icon;
    @Nullable
    protected String bodyImageUrl;

    /**
     * Default construct for new notification
     */
    public BaseTemplate() {
    }

    public BaseTemplate(RichTextElement application, @Nullable RichTextElement title, @Nullable List<RichTextElement> body,
            String icon, @Nullable String bodyImageUrl) {
        this.application = Objects.requireNonNull(application, "Application can't be null.");
        this.title = title;
        this.body = body;
        this.icon = Objects.requireNonNull(icon, "Icon href can't be null.");
        this.bodyImageUrl = bodyImageUrl;
    }

    @Nullable
    public RichTextElement getApplication() {
        return application;
    }

    public void setApplication(RichTextElement application) {
        this.application = Objects.requireNonNull(application, "Application can't be null.");
    }

    @Nullable
    public RichTextElement getTitle() {
        return title;
    }

    public void setTitle(@Nullable RichTextElement title) {
        this.title = title;
    }

    @Nullable
    public List<RichTextElement> getBody() {
        return body == null ? Collections.emptyList() : body;
    }

    public void setBody(@Nullable List<RichTextElement> body) {
        this.body = body;
    }

    @Nullable
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = Objects.requireNonNull(icon, "Icon href can't be null.");
    }

    @Nullable
    public String getBodyImageUrl() {
        return JsonUtils.getStringTrimOrNull(bodyImageUrl);
    }

    public void setBodyImageUrl(@Nullable String bodyImageUrl) {
        this.bodyImageUrl = bodyImageUrl;
    }
}

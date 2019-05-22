package notification.service.domain.notification.rich;

import notification.service.domain.notification.rich.component.RichTextElement;

import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RichTemplate {
    private final String templateId;
    private final String templateTitle;
    private final String internalTitle;
    private final List<RichTextElement> elements;

    public RichTemplate(@JsonProperty("templateId") String templateId,
            @JsonProperty("templateTitle") String templateTitle,
            @JsonProperty("internalTitle") String internalTitle,
            @JsonProperty("elements") List<RichTextElement> elements) {
        this.templateId = Objects.requireNonNull(templateId, "Template id can't be null.");
        this.templateTitle = Objects.requireNonNull(templateTitle, "Template title can't be null.");
        this.internalTitle = Objects.requireNonNull(internalTitle, "Internal title can't be null.");
        this.elements = Objects.requireNonNull(elements, "Elements can't be null.");
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getTemplateTitle() {
        return templateTitle;
    }

    public String getInternalTitle() {
        return internalTitle;
    }

    public List<RichTextElement> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return "RichTemplate{" +
                "templateId='" + templateId + '\'' +
                ", templateTitle='" + templateTitle + '\'' +
                ", internalTitle='" + internalTitle + '\'' +
                ", elements=" + elements +
                '}';
    }
}

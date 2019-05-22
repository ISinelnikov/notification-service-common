package notification.service.domain.notification.rich;

import notification.service.domain.notification.rich.component.RichTextElement;

import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RichTemplate {
    private final String templateId;
    private final List<RichTextElement> elements;

    public RichTemplate(@JsonProperty("templateId") String templateId,
            @JsonProperty("elements") List<RichTextElement> elements) {
        this.templateId = Objects.requireNonNull(templateId, "Template id can't be null.");
        this.elements = Objects.requireNonNull(elements, "Elements can't be null.");
    }

    public String getTemplateId() {
        return templateId;
    }

    public List<RichTextElement> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return "RichTemplate{" +
                "templateId='" + templateId + '\'' +
                ", elements=" + elements +
                '}';
    }
}

package notification.service.domain.notification.rich;

import notification.service.domain.notification.rich.component.RichTextElement;

import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RichTemplate {
    private final List<RichTextElement> elements;

    public RichTemplate(@JsonProperty("elements") List<RichTextElement> elements) {
        this.elements = Objects.requireNonNull(elements, "Elements can't be null.");
    }

    public List<RichTextElement> getElements() {
        return elements;
    }

    @Override
    public String toString() {
        return "RichTemplate{" +
                "elements=" + elements +
                '}';
    }
}

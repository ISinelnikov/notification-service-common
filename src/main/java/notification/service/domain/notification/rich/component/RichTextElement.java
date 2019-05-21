package notification.service.domain.notification.rich.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RichTextElement {
    private final String text;
    @Nullable
    private Map<String, Object> attribute;

    public RichTextElement(String text) {
        this(text, null);
    }

    @JsonCreator
    public RichTextElement(
            @JsonProperty("text") String text,
            @JsonProperty("attribute") @Nullable Map<String, Object> attribute) {
        this.text = Objects.requireNonNull(text, "Text can't be null.");
        this.attribute = attribute;
    }

    public String getText() {
        return text;
    }

    public Map<String, Object> getAttribute() {
        return attribute;
    }

    public void setProperty(TextProperty property, Object value) {
        if (attribute == null) {
            attribute = new HashMap<>();
        }
        attribute.put(property.getAlias(), value);
    }

    public void removeProperty(TextProperty property) {
        if (attribute != null) {
            attribute.remove(property.getAlias());
        }
    }

    @Override
    public String toString() {
        return "RichTextElement{" +
                "text='" + text + '\'' +
                ", attribute=" + attribute +
                '}';
    }

    public enum TextProperty {
        COLOR("color"),
        BOLD("bold"),
        ITALIC("italic");

        private final String alias;

        TextProperty(String alias) {
            this.alias = alias;
        }

        public String getAlias() {
            return alias;
        }
    }
}
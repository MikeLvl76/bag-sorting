import java.util.HashMap;
import java.util.Map;

public abstract class Shape {
    protected ShapeType type;
    protected Map<String, Double> attributes = new HashMap<>();

    protected String getName() {
        return this.type.getName();
    }

    protected int getDimension() {
        return this.type.getDimension();
    }

    protected Map.Entry<String, Double> getAttribute(String key) {
        Map.Entry<String, Double> attribute = null;
        for (Map.Entry<String, Double> entry : attributes.entrySet()) {
            if (entry.getKey().equals(key)) {
                attribute = entry;
                break;
            }
        }
        return attribute;
    }

    protected double getAttributeValue(String key) {
        if (!this.attributes.containsKey(key))
            return 0.0;
        return this.attributes.get(key);
    }

    protected Map<String, Double> getAttributes() {
        return this.attributes;
    }

    protected void setType(ShapeType type) {
        this.type = type;
    }

    protected void updateAttributes(Map<String, Double> map) {
        if (map == null || map.size() == 0) {
            return;
        }
        if (this.attributes.isEmpty()) {
            this.attributes.putAll(map);
            return;
        }
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (!this.attributes.containsKey(entry.getKey())) {
                this.attributes.put(entry.getKey(), entry.getValue());
                continue;
            }
            this.attributes.replace(entry.getKey(), entry.getValue());
        }
    }

    public String minimalDisplay() {
        return String.format("%s_%dD (%.2f)", this.type.getName().substring(0, 3).toUpperCase(),
                this.type.getDimension(),
                this.type.getDimension() == 2 ? this.attributes.get("area") : this.attributes.get("volume"));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n  {\n   ");
        builder.append(String.format("name: %s,\n", this.type.getName()));
        builder.append(String.format("   dimension: %d,\n", this.type.getDimension()));
        int index = 0;
        for (Map.Entry<String, Double> entry : this.attributes.entrySet()) {
            String formatted = "   %s: %.3g";
            if (index < this.attributes.size() - 1) {
                formatted += ",\n";
            }
            builder.append(String.format(formatted, entry.getKey(), entry.getValue()));
            index++;
        }
        builder.append("\n  }");

        return builder.toString();
    }
}

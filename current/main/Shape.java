import java.util.HashMap;
import java.util.Map;

public abstract class Shape {
    protected String name;
    protected int dimension;
    protected Boolean canStack;
    protected Map<String, Double> attributes = new HashMap<>();

    protected String getName() {
        return this.name;
    }

    protected int getDimension() {
        return this.dimension;
    }

    protected Boolean isStackable() {
        return this.canStack;
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
        if (!this.attributes.containsKey(key)) return 0.0;
        return this.attributes.get(key);
    }

    protected Map<String, Double> getAttributes() {
        return this.attributes;
    }
    
    protected void setName(String n) {
        this.name = n;
    }

    protected void setDimension(int d) {
        this.dimension = d;
    }

    protected void setCanStack(Boolean b) {
        this.canStack = b;
    }

    protected void updateAttributes(Map<String, Double> map){
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

    public char minimalDisplay() {
        return Character.toUpperCase(this.name.charAt(0));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n  {\n   ");
        builder.append(String.format("name: %s,\n", this.name));
        builder.append(String.format("   dimension: %s,\n", this.dimension));
        int index = 0;
        for (Map.Entry<String, Double> entry : this.attributes.entrySet()) {
            String format = "   %s: %.2f";
            if (index < this.attributes.size() - 1) {
                format += ",\n";  
            }
            builder.append(String.format(format, entry.getKey(), entry.getValue()));
            index++;
        }
        builder.append("\n  }");

        return builder.toString();
    }
}

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ShapeGenerator {
    
    private final Random random = new Random();
    private final Map<ShapeType, String[]> attributes;
    private ArrayList<Shape> shapes;

    public ShapeGenerator() {
        this.attributes = new HashMap<>();
        this.shapes = new ArrayList<>();
        this.initAttributes();
    }

    private void initAttributes() {
        // 2D Shapes attributes
        this.attributes.put(ShapeType.SQUARE, new String[]{"width", "length"});
        this.attributes.put(ShapeType.TRIANGLE, new String[]{"base", "length"});
        this.attributes.put(ShapeType.CIRCLE, new String[]{"radius"});
        this.attributes.put(ShapeType.TRAPEZIUM, new String[]{"a", "b", "height"});
        this.attributes.put(ShapeType.PARALLELOGRAM, new String[]{"base", "height"});
        this.attributes.put(ShapeType.RHOMBUS, new String[]{"base", "height"});
        this.attributes.put(ShapeType.POLYGON, new String[]{"sides", "length"});

        // 3D Shapes attributes
        this.attributes.put(ShapeType.CUBE, new String[]{"width"});
        this.attributes.put(ShapeType.RECT, new String[]{"width", "length", "depth"});
        this.attributes.put(ShapeType.CYLINDER, new String[]{"radius", "height"});
        this.attributes.put(ShapeType.CONE, new String[]{"radius", "height"});
        this.attributes.put(ShapeType.SPHERE, new String[]{"radius"});
        this.attributes.put(ShapeType.PYRAMID, new String[]{"base-area", "height"});
        this.attributes.put(ShapeType.PRISM, new String[]{"polygon-base-area", "height"});
    }

    public ArrayList<Shape> getShapes() {
        return this.shapes;
    }

    private ShapeType type() {
        int size = ShapeType.getTotalSize();
        int index = random.nextInt(size);
        return ShapeType.getValue(index);
    }

    private Map<String, Double> attributes(ShapeType type) {
        String[] names = this.attributes.entrySet().stream().filter(entry -> entry.getKey().equals(type)).map(entry -> entry.getValue()).findFirst().get();
        Map<String, Double> map = new HashMap<>(names.length);
        for (String name : names) {
            double value = new BigDecimal(this.random.nextDouble(0.0, 10.0)).setScale(2, RoundingMode.DOWN).doubleValue();
            map.put(name, value);
        }
        return map;
    }

    public Shape shape(ShapeType type) {
        int dim = type.getDimension();
        if (dim == 2) return new Shape2D(type, attributes(type));
        if (dim == 3) return new Shape3D(type, attributes(type));
        return null;
    }

    public void shapes(int number) {
        this.shapes.ensureCapacity(number);

        int index = number;
        while(index > 0) {
            ShapeType type = this.type();
            Shape shape = this.shape(type);
            if (shape != null) {
                this.shapes.add(this.shape(type));
                index--;
            }   
        }
    }
}

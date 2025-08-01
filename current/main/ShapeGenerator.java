import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ShapeGenerator {
    
    private final Random random = new Random();
    private final Map<String, String[]> attributes = new HashMap<>();
    private int number;
    private ArrayList<Shape> shapes;

    public ShapeGenerator(int number) {
        this.number = number;
        this.shapes = new ArrayList<>();
        this.shapes.ensureCapacity(this.number);
        this.initAttributes();
    }

    private void initAttributes() {
        String[] squareAttrs = {"width", "length"};
        String[] triangleAttrs = {"base", "length"};
        String[] circleAttrs = {"radius"};
        String[] trapeziumAttrs = {"a", "b", "height"};
        String[] parallelogramAttrs = {"base", "height"};
        String[] rhombusAttrs = {"base", "height"};
        String[] polygonAttrs = {"sides", "length"};

        String[] cubeAttrs = {"width"};
        String[] rectAttrs = {"width", "length", "depth"};
        String[] cylinderAttrs = {"radius", "height"};
        String[] coneAttrs = {"radius", "height"};
        String[] sphereAttrs = {"radius"};
        String[] pyramidAttrs = {"base-area", "height"};
        String[] prismAttrs = {"polygon-base-area", "height"};

        this.attributes.put("square", squareAttrs);
        this.attributes.put("triangle", triangleAttrs);
        this.attributes.put("circle", circleAttrs);
        this.attributes.put("trapezium", trapeziumAttrs);
        this.attributes.put("parallelogram", parallelogramAttrs);
        this.attributes.put("rhombus", rhombusAttrs);
        this.attributes.put("polygon", polygonAttrs);

        this.attributes.put("cube", cubeAttrs);
        this.attributes.put("rect", rectAttrs);
        this.attributes.put("cylinder", cylinderAttrs);
        this.attributes.put("cone", coneAttrs);
        this.attributes.put("sphere", sphereAttrs);
        this.attributes.put("pyramid", pyramidAttrs);
        this.attributes.put("prism", prismAttrs);
    }

    public ArrayList<Shape> getShapes() {
        return this.shapes;
    }

    private ShapeType name(int dimension) {
        int size = ShapeType.getSize(dimension);
        int index = random.nextInt(size);
        return ShapeType.getValue(index);
    }

    private String[] attributesName(int dimension) {
        ShapeType value = name(dimension);
        return this.attributes.get(value.getName());
    }

    private Map<String, Double> attributesValue(int dimension) {
        String[] names = attributesName(dimension);
        Map<String, Double> map = new HashMap<>();
        for (String name : names) {
            map.put(name, this.random.nextDouble(0.0, 100));
        }
        return map;
    }

    public Shape shape(int dimension) {
        ShapeType value = this.name(dimension);
        if (dimension == 2) return new Shape2D(value.getName(), attributesValue(dimension));
        if (dimension == 3) return new Shape3D(value.getName(), attributesValue(dimension));
        return null;
    }

    public void shapes(int dimension) {
        for (int i = 0; i < this.number ; i++) {
            if (dimension == 2) {
                Shape2D shape = (Shape2D) this.shape(dimension);
                this.shapes.add(shape);
                continue;
            }
            if (dimension == 3) {
                Shape3D shape = (Shape3D) this.shape(dimension);
                this.shapes.add(shape);
            }
        }
    }

    public void shapes() {
        for (int i = 0; i < this.number ; i++) {
            Boolean b = random.nextBoolean();
            if (b) {
                Shape2D shape = (Shape2D) this.shape(2);
                this.shapes.add(shape);
                continue;
            }
            if (!b) {
                Shape3D shape = (Shape3D) this.shape(3);
                this.shapes.add(shape);
            }
        }
    }
}

import java.util.Map;

public class Shape2D extends Shape implements AreaCalculator {

    public Shape2D(ShapeType type, Map<String, Double> map) {
        this.setType(type);
        if (type.getDimension() != 2) throw new IllegalArgumentException("Dimension must be equals to 2.");
        this.setCanStack(false);
        map.put("area", 0.0);
        this.calculateArea();
        this.updateAttributes(map);
    }

    public double getArea() {
        return this.attributes.get("area");
    }

    private void calculateArea() {
        switch (this.type) {
            case SQUARE:
                double squareArea = getSquareArea(this.getAttributeValue("width"), this.getAttributeValue("length"));
                this.attributes.put("area", squareArea);
                break;

            case TRIANGLE:
                double triangleArea = getTriangleArea(this.getAttributeValue("base"), this.getAttributeValue("length"));
                this.attributes.put("area", triangleArea);
                break;

            case CIRCLE:
                double circleArea = getCircleArea(this.getAttributeValue("radius"));
                this.attributes.put("area", circleArea);
                break;

            case TRAPEZIUM:
                double trapeziumArea = getTrapeziumArea(this.getAttributeValue("a"), this.getAttributeValue("b"), this.getAttributeValue("height"));
                this.attributes.put("area", trapeziumArea);
                break;

            case PARALLELOGRAM:
                double parallelogramArea = getParallelogramArea(this.getAttributeValue("base"), this.getAttributeValue("height"));
                this.attributes.put("area", parallelogramArea);
                break;

            case RHOMBUS:
                double rhombusArea = getRhombusArea(this.getAttributeValue("base"), this.getAttributeValue("height"));
                this.attributes.put("area", rhombusArea);
                break;

            case POLYGON:
                double polygonArea = getRegularPolygonArea((int) this.getAttributeValue("sides"), this.getAttributeValue("length"));
                this.attributes.put("area", polygonArea);
                break;
        
            default:
                break;
        }
    }
}

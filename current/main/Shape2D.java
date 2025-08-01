import java.util.Map;

public class Shape2D extends Shape implements AreaCalculator {

    public Shape2D(String _name, Map<String, Double> map) {
        this.setName(_name);
        this.setDimension(2);
        this.setCanStack(false);
        map.put("area", 0.0);
        this.updateAttributes(map);
        this.calculateArea();
    }

    public double getArea() {
        return this.attributes.get("area");
    }

    private void calculateArea() {
        switch (this.name) {
            case "square":
                double squareArea = getSquareArea(this.getAttributeValue("width"), this.getAttributeValue("length"));
                this.attributes.replace("area", squareArea);
                break;

            case "triangle":
                double triangleArea = getTriangleArea(this.getAttributeValue("base"), this.getAttributeValue("length"));
                this.attributes.replace("area", triangleArea);
                break;

            case "circle":
                double circleArea = getCircleArea(this.getAttributeValue("radius"));
                this.attributes.replace("area", circleArea);
                break;

            case "trapezium":
                double trapeziumArea = getTrapeziumArea(this.getAttributeValue("a"), this.getAttributeValue("b"), this.getAttributeValue("height"));
                this.attributes.replace("area", trapeziumArea);
                break;

            case "parallelogram":
                double parallelogramArea = getParallelogramArea(this.getAttributeValue("base"), this.getAttributeValue("height"));
                this.attributes.replace("area", parallelogramArea);
                break;

            case "rhombus":
                double rhombusArea = getRhombusArea(this.getAttributeValue("base"), this.getAttributeValue("height"));
                this.attributes.replace("area", rhombusArea);
                break;

            case "polygon":
                double polygonArea = getRegularPolygonArea((int) this.getAttributeValue("sides"), this.getAttributeValue("length"));
                this.attributes.replace("area", polygonArea);
                break;
        
            default:
                break;
        }
    }
}

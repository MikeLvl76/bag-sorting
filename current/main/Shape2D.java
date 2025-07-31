import java.util.Map;

public class Shape2D extends Shape implements AreaCalculator {

    private double area;

    public Shape2D(String _name, Map<String, Double> map) {
        this.setName(_name);
        this.setDimension(2);
        this.setCanStack(false);
        this.updateAttributes(map);
        this.area = 0.0;
    }

    public double getArea() {
        return this.area;
    }

    public void calculateArea() {
        switch (this.name) {
            case "square":
                this.area = getSquareArea(this.getAttributeValue("width"), this.getAttributeValue("length"));
                break;

            case "triangle":
                this.area = getTriangleArea(this.getAttributeValue("base"), this.getAttributeValue("length"));
                break;

            case "circle":
                this.area = getCircleArea(this.getAttributeValue("radius"));
                break;

            case "trapezium":
                this.area = getTrapeziumArea(this.getAttributeValue("a"), this.getAttributeValue("b"), this.getAttributeValue("height"));
                break;

            case "parallelogram":
                this.area = getParallelogramArea(this.getAttributeValue("base"), this.getAttributeValue("height"));
                break;

            case "rhombus":
                this.area = getRhombusArea(this.getAttributeValue("base"), this.getAttributeValue("height"));
                break;

            case "polygon":
                this.area = getRegularPolygonArea((int) this.getAttributeValue("sides"), this.getAttributeValue("length"));
                break;
        
            default:
                this.area = 0.0;
                break;
        }
    }
}

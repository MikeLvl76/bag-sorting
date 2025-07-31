import java.util.Map;

public class Surface extends Shape implements AreaSurfaceCalculator {

    private double area;

    public Surface(String _name, Map<String, Double> map) {
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
                double width = this.getAttributeValue("width");
                double length = this.getAttributeValue("length");
                this.area = getSquareArea(width, length);
                break;

            case "triangle":
                double base = this.getAttributeValue("base");
                double l = this.getAttributeValue("length");
                this.area = getTriangleArea(base, l);
                break;

            case "circle":
                double radius = this.getAttributeValue("radius");
                this.area = getCircleArea(radius);
                break;


            case "trapezium":
                double a = this.getAttributeValue("a");
                double b = this.getAttributeValue("b");
                double height = this.getAttributeValue("height");
                this.area = getTrapeziumArea(a, b, height);
                break;


            case "parallelogram":
                double _base = this.getAttributeValue("base");
                double h = this.getAttributeValue("h");
                this.area = getParallelogramArea(_base, h);
                break;


            case "rhombus":
                double _b = this.getAttributeValue("base");
                double _h = this.getAttributeValue("h");
                this.area = getRhombusArea(_b, _h);
                break;


            case "polygon":
                int sides = (int) this.getAttributeValue("sides");
                double _length = this.getAttributeValue("length");
                this.area = getRegularPolygonArea(sides, _length);
                break;
        
            default:
                this.area = 0.0;
                break;
        }
    }
}

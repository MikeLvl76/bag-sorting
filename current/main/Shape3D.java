import java.util.Map;

public class Shape3D extends Shape {
    private double area;

    public Shape3D(String _name, Map<String, Double> map) {
        this.setName(_name);
        this.setDimension(3);
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
                
                break;

            case "triangle":
                
                break;

            case "circle":
                
                break;


            case "trapezium":
                
                break;


            case "parallelogram":
                
                break;


            case "rhombus":
                
                break;


            case "polygon":
                
                break;
        
            default:
                this.area = 0.0;
                break;
        }
    }
}

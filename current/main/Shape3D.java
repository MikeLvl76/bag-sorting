import java.util.Map;

public class Shape3D extends Shape implements VolumeCalculator {

    public Shape3D(ShapeType type, Map<String, Double> attrs) {
        this.type = type;
        if (type.getDimension() != 3) throw new IllegalArgumentException("Dimension must be equals to 3.");
        this.updateAttributes(attrs);
        this.calculate();
    }

    private double getCubeVolume(double length) {
        return Math.pow(length, 3);
    }

    private double getRectVolume(double width, double length, double depth) {
        return width * length * depth;
    }

    private double getCylinderVolume(double radius, double height) {
        return Math.PI * Math.pow(radius, 2) * height;
    }

    private double getConeVolume(double radius, double height) {
        return 1/3 * Math.PI * Math.pow(radius, 2) * height;
    }

    private double getSphereVolume(double radius) {
        return 4.0/3 * Math.PI * Math.pow(radius, 3);
    }

    private double getPyramidVolume(double baseArea, double height) {
        return 1.0/3 * baseArea * height;
    }

    private double getPrismVolume(double basePolygonArea, double height) {
        return basePolygonArea * height;
    }

    public void calculate() {
        double volume = 0.0;
        switch (this.type) {
            case CUBE:
                volume = getCubeVolume(this.attributes.get("length"));
                break;

            case RECT:
                volume = getRectVolume(this.attributes.get("width"), this.attributes.get("length"), this.attributes.get("depth"));
                break;

            case CYLINDER:
                volume = getCylinderVolume(this.attributes.get("radius"), this.attributes.get("height"));
                break;

            case CONE:
                volume = getConeVolume(this.attributes.get("radius"), this.attributes.get("height"));
                break;

            case SPHERE:
                volume = getSphereVolume(this.attributes.get("radius"));
                break;

            case PYRAMID:
                volume = getPyramidVolume(this.attributes.get("base-area"), this.attributes.get("height"));
                break;

            case PRISM:
                volume = getPrismVolume(this.attributes.get("polygon-base-area"), this.attributes.get("height"));
                break;
        
            default:
                volume = 0.0;
                break;
        }
        this.attributes.put("volume", volume);
    }
}

import java.util.Map;

public class Shape3D extends Shape implements VolumeCalculator {
    private double volume;

    public Shape3D(String _name, Map<String, Double> map) {
        this.setName(_name);
        this.setDimension(3);
        this.setCanStack(false);
        this.updateAttributes(map);
        this.volume = 0.0;
    }

    public double getVolume() {
        return this.volume;
    }

    public void calculateVolume() {
        switch (this.name) {
            case "cube":
                double length = this.getAttributeValue("length");
                this.volume = getCubeVolume(length);
                break;

            case "rect":
                double width = this.getAttributeValue("width");
                double _length = this.getAttributeValue("length");
                double depth = this.getAttributeValue("depth");
                this.volume = getRectVolume(width, _length, depth);
                break;

            case "cylinder":
                double radius = this.getAttributeValue("radius");
                double height = this.getAttributeValue("height");
                this.volume = getCylinderVolume(radius, height);
                break;


            case "cone":
                double r = this.getAttributeValue("radius");
                double h = this.getAttributeValue("height");
                this.volume = getConeVolume(r, h);
                break;


            case "sphere":
                double _radius = this.getAttributeValue("radius");
                this.volume = getSphereVolume(_radius);
                break;


            case "pyramid":
                double baseArea = this.getAttributeValue("base-area");
                double _h = this.getAttributeValue("height");
                this.volume = getPyramidVolume(baseArea, _h);
                break;


            case "prism":
                double polygonBaseArea = this.getAttributeValue("polygon-base-area");
                double _height = this.getAttributeValue("height");
                this.volume = getPrismVolume(polygonBaseArea, _height);
                break;
        
            default:
                this.volume = 0.0;
                break;
        }
    }
}

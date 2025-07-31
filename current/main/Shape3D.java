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
                this.volume = getCubeVolume(this.getAttributeValue("length"));
                break;

            case "rect":
                this.volume = getRectVolume(this.getAttributeValue("width"), this.getAttributeValue("length"), this.getAttributeValue("depth"));
                break;

            case "cylinder":
                this.volume = getCylinderVolume(this.getAttributeValue("radius"), this.getAttributeValue("height"));
                break;


            case "cone":
                this.volume = getConeVolume(this.getAttributeValue("radius"), this.getAttributeValue("height"));
                break;


            case "sphere":
                this.volume = getSphereVolume(this.getAttributeValue("radius"));
                break;


            case "pyramid":
                this.volume = getPyramidVolume(this.getAttributeValue("base-area"), this.getAttributeValue("height"));
                break;


            case "prism":
                this.volume = getPrismVolume(this.getAttributeValue("polygon-base-area"), this.getAttributeValue("height"));
                break;
        
            default:
                this.volume = 0.0;
                break;
        }
    }
}

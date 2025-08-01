import java.util.Map;

public class Shape3D extends Shape implements VolumeCalculator {

    public Shape3D(String _name, Map<String, Double> map) {
        this.setName(_name);
        this.setDimension(3);
        this.setCanStack(false);
        map.put("volume", 0.0);
        this.updateAttributes(map);
        this.calculateVolume();
    }

    public double getVolume() {
        return this.attributes.get("volume");
    }

    private void calculateVolume() {
        switch (this.name) {
            case "cube":
                double cubeVolume = getCubeVolume(this.getAttributeValue("length"));
                this.attributes.replace("volume", cubeVolume);
                break;

            case "rect":
                double rectVolume = getRectVolume(this.getAttributeValue("width"), this.getAttributeValue("length"), this.getAttributeValue("depth"));
                this.attributes.replace("volume", rectVolume);
                break;

            case "cylinder":
                double cylinderVolume = getCylinderVolume(this.getAttributeValue("radius"), this.getAttributeValue("height"));
                this.attributes.replace("volume", cylinderVolume);
                break;

            case "cone":
                double coneVolume = getConeVolume(this.getAttributeValue("radius"), this.getAttributeValue("height"));
                this.attributes.replace("volume", coneVolume);
                break;

            case "sphere":
                double sphereVolume = getSphereVolume(this.getAttributeValue("radius"));
                this.attributes.replace("volume", sphereVolume);
                break;

            case "pyramid":
                double pyramidVolume = getPyramidVolume(this.getAttributeValue("base-area"), this.getAttributeValue("height"));
                this.attributes.replace("volume", pyramidVolume);
                break;

            case "prism":
                double prismVolume = getPrismVolume(this.getAttributeValue("polygon-base-area"), this.getAttributeValue("height"));
                this.attributes.replace("volume", prismVolume);
                break;
        
            default:
                break;
        }
    }
}

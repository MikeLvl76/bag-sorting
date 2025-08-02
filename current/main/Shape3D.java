import java.util.Map;

public class Shape3D extends Shape implements VolumeCalculator {

    public Shape3D(ShapeType type, Map<String, Double> map) {
        this.type = type;
        if (type.getDimension() != 3) throw new IllegalArgumentException("Dimension must be equals to 3.");
        this.setCanStack(false);
        this.calculateVolume();
        this.updateAttributes(map);
    }

    public double getVolume() {
        return this.attributes.get("volume");
    }

    private void calculateVolume() {
        switch (this.type) {
            case CUBE:
                double cubeVolume = getCubeVolume(this.getAttributeValue("length"));
                this.attributes.put("volume", cubeVolume);
                break;

            case RECT:
                double rectVolume = getRectVolume(this.getAttributeValue("width"), this.getAttributeValue("length"), this.getAttributeValue("depth"));
                this.attributes.put("volume", rectVolume);
                break;

            case CYLINDER:
                double cylinderVolume = getCylinderVolume(this.getAttributeValue("radius"), this.getAttributeValue("height"));
                this.attributes.put("volume", cylinderVolume);
                break;

            case CONE:
                double coneVolume = getConeVolume(this.getAttributeValue("radius"), this.getAttributeValue("height"));
                this.attributes.put("volume", coneVolume);
                break;

            case SPHERE:
                double sphereVolume = getSphereVolume(this.getAttributeValue("radius"));
                this.attributes.put("volume", sphereVolume);
                break;

            case PYRAMID:
                double pyramidVolume = getPyramidVolume(this.getAttributeValue("base-area"), this.getAttributeValue("height"));
                this.attributes.put("volume", pyramidVolume);
                break;

            case PRISM:
                double prismVolume = getPrismVolume(this.getAttributeValue("polygon-base-area"), this.getAttributeValue("height"));
                this.attributes.put("volume", prismVolume);
                break;
        
            default:
                break;
        }
    }
}

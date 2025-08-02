import java.util.Map;

public class Shape3D extends Shape implements VolumeCalculator {

    public Shape3D(ShapeType type, Map<String, Double> attrs) {
        this.type = type;
        if (type.getDimension() != 3) throw new IllegalArgumentException("Dimension must be equals to 3.");
        this.setCanStack(false);
        attrs.put("volume", this.calculateVolume(attrs));
        this.updateAttributes(attrs);
    }

    private double calculateVolume(Map<String, Double> attrs) {
        switch (this.type) {
            case CUBE:
                return getCubeVolume(attrs.get("length"));

            case RECT:
                return getRectVolume(attrs.get("width"), attrs.get("length"), attrs.get("depth"));

            case CYLINDER:
                return getCylinderVolume(attrs.get("radius"), attrs.get("height"));

            case CONE:
                return getConeVolume(attrs.get("radius"), attrs.get("height"));

            case SPHERE:
                return getSphereVolume(attrs.get("radius"));

            case PYRAMID:
                return getPyramidVolume(attrs.get("base-area"), attrs.get("height"));

            case PRISM:
                return getPrismVolume(attrs.get("polygon-base-area"), attrs.get("height"));
        
            default:
                return 0.0;
        }
    }
}

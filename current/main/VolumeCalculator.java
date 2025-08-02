public interface VolumeCalculator {
    default double getCubeVolume(double length) {
        return Math.pow(length, 3);
    }

    default double getRectVolume(double width, double length, double depth) {
        return width * length * depth;
    }

    default double getCylinderVolume(double radius, double height) {
        return Math.PI * Math.pow(radius, 2) * height;
    }

    default double getConeVolume(double radius, double height) {
        return 1/3 * Math.PI * Math.pow(radius, 2) * height;
    }

    default double getSphereVolume(double radius) {
        return 4.0/3 * Math.PI * Math.pow(radius, 3);
    }

    default double getPyramidVolume(double baseArea, double height) {
        return 1.0/3 * baseArea * height;
    }

    default double getPrismVolume(double basePolygonArea, double height) {
        return basePolygonArea * height;
    }
}

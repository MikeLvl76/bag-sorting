public interface AreaCalculator {
    default double getSquareArea(double width, double length) {
        return width * length;
    }

    default double getTriangleArea(double base, double height) {
        return 1/2 * (base * height);
    }

    default double getCircleArea(double radius) {
        return Math.PI * Math.pow(radius, 2);
    }

    default double getTrapeziumArea(double a, double b, double height) {
        return 1/2 * (a + b) * height;
    }

    default double getParallelogramArea(double base, double height) {
        return base * height;
    }

    default double getRhombusArea(double base, double height) {
        return base * height;
    }

    default double getRegularPolygonArea(int sides, double length) {
        if (sides == 0 || length == 0) return 0.0;
        return (Math.pow(sides * length, 2) * (1 / Math.tan(Math.PI / sides))) / 4;
    }
}

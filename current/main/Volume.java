public class Volume extends Shape {
    public Volume(String _name) {
        this.setName(_name);
        this.setDimension(3);
        this.setCanStack(false);
    }
}

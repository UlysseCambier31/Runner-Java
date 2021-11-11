public class Camera {
    private double x;
    private double y;
    private double v_x;
    private double v_y;


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }



    public Camera(double x, double y) {
        this.x = x;
        this.y = y;
        this.v_x = 10;
        this.v_y=10;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void update(long time,Hero heros){
        double a_x = (heros.getX()-this.x)+1.2*this.v_x;
        double a_y = (heros.getY()-this.y)+1.2*this.v_y;
        this.v_x = a_x*16*(10^(-3))+this.v_x;
        this.v_y = a_y*16*(10^(-3))+this.v_y;
        this.x = this.v_x *16*(10^(-3))+this.x;
        this.y = this.v_y*16*(10^(-3))+this.y;
    }
}

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

    public double getVx(){return v_x;}


    public Camera(double x, double y) {
        this.x = x;
        this.y = y;
        this.v_x = 0;
        this.v_y=0;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void update(long time,Hero heros){
        double a_x = (heros.getX()-x)-1.2*v_x;
        double a_y = (heros.getY()-y)-1.2*v_y;
        v_x = a_x*16*(Math.pow(10,-3))+v_x;
        v_y = a_y*16*(Math.pow(10,-3))+v_y;
        x = v_x *16*(Math.pow(10,-3))+x;
        y = v_y*16*(Math.pow(10,-3))+y;
    }
}

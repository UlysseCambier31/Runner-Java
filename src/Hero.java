import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class Hero extends AnimatedThing {
    public Hero(double x, double y, ImageView imgView, int attitude) {
        super(x, y, imgView, attitude);
        imgView.setViewport(new Rectangle2D(20,0,65,100));
        imgView.setX(this.getX());
        imgView.setY(this.getY());
        this.setFrameduration(500);
        this.setIndex(0);
        this.setMaxindex(5);
    }
    public void update(long time){
        if ((time%this.getFrameduration())==0) this.setIndex(this.getIndex() + 1);
        if (this.getIndex()>this.getMaxindex()) this.setIndex(0);
        this.getImgView().setViewport(new Rectangle2D(0+(85*this.getIndex()),0,85,100));

    }
}

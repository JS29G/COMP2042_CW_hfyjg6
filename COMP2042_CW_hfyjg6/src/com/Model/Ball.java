package com.Model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

abstract public class Ball {

    private Shape ballFace;

    private Point2D center;

    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;

        this.up = new Point2D.Double(center.getX(),center.getY()-(radiusB / 2));
        this.down = new Point2D.Double(center.getX(),center.getY()+(radiusB / 2));
        this.left = new Point2D.Double(center.getX()-(radiusA /2),center.getY());
        this.right = new Point2D.Double(center.getX()+(radiusA /2),center.getY());

        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    //Movement
    public void move(){
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        getShape();
    }

    public void moveTo(Point p){
        center.setLocation(p);
        getShape();
    }

    //Shape
    private void getShape(){
        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();
        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);
        ballFace = tmp;
    }

    //Location
    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    //speed
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }
    public void setXSpeed(int s){
        speedX = s;
    }
    public void setYSpeed(int s){
        speedY = s;
    }
    public void reverseX(){
        speedX *= -1;
    }
    public void reverseY(){
        speedY *= -1;
    }
    public int getSpeedX(){
        return speedX;
    }
    public int getSpeedY(){ return speedY; }

    //Appearance
    public Color getBorderColor(){
        return border;
    }
    public Color getInnerColor(){
        return inner;
    }
    public Shape getBallFace(){
        return ballFace;
    }

    //Location
    public Point2D getPosition(){
        return center;
    }
    public Point2D getUp() {
        return up;
    }
    public Point2D getDown() {
        return down;
    }
    public Point2D getLeft() {
        return left;
    }
    public Point2D getRight() {
        return right;
    }
}


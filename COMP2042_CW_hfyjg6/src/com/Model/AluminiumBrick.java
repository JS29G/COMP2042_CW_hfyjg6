package com.Model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class AluminiumBrick extends Brick {
    private static final Color DEF_INNER = Color.decode("#bdbcb1");
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int ALUMINIUM_STRENGTH = 1;
    private static final double ALUMINIUM_PROBABILITY = 0.4;

    private Random rnd;
    private Shape brickFace;

    //Create Brick
    public AluminiumBrick(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,ALUMINIUM_STRENGTH);
        rnd = new Random();
        brickFace = super.brickFace;
    }

    //Replace
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    //Brick-face
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    //Impact
    public  boolean setImpact(Point2D point , int dir){
        if(!super.isBroken())
            return false;
        impact();
        return !super.isBroken();
    }

    public void impact(){
        if(rnd.nextDouble() < ALUMINIUM_PROBABILITY){
            super.impact();
        }
    }

}

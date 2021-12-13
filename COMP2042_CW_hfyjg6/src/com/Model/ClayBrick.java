package com.Model;

import java.awt.*;
import java.awt.Point;

public class ClayBrick extends Brick {
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;

    //Replace
    public ClayBrick(Point point, Dimension size){
        super(point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    //Brick-face
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    //Reset
    @Override
    public Shape getBrick() {
        return super.brickFace;
    }
}

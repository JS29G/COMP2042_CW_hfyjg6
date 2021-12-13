package com.Model;

import java.awt.*;

public class Levels {
    private static final int LEVELS_COUNT = 5;
    private static final int CLAY = 1;
    private static final int CEMENT = 2;
    private static final int ALUMINIUM = 3;
    private static final int STEEL = 4;


     Brick[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio) {
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea, brickCount, lineCount, brickDimensionRatio);
        tmp[1] = makeDoubleTypeLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, CEMENT);
        tmp[2] = makeTripleTypeLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, CEMENT, ALUMINIUM);
        tmp[3] = makeDoubleTypeLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CEMENT, ALUMINIUM);
        tmp[3] = makeTripleTypeLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CEMENT, ALUMINIUM, STEEL);
        return tmp;
    }

    //1 brick level
    private Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio) {
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();

        int i;
        for (i = 0; i < tmp.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);
            tmp[i] = makeBrick(p, brickSize, Levels.CLAY);
        }

        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            tmp[i] = new ClayBrick(p, brickSize);
        }
        return tmp;
    }

    //2 brick level
    private Brick[] makeDoubleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB) {
    /*
      if brickCount is not divisible by line count,brickCount is adjusted to the biggest
      multiple of lineCount smaller then brickCount
     */
    brickCnt -= brickCnt % lineCnt;

    int brickOnLine = brickCnt / lineCnt;

    int centerLeft = brickOnLine / 2 - 1;
    int centerRight = brickOnLine / 2 + 1;

    double brickLen = drawArea.getWidth() / brickOnLine;
    double brickHgt = brickLen / brickSizeRatio;

    brickCnt += lineCnt / 2;

    Brick[] tmp = new Brick[brickCnt];

    Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
    Point p = new Point();

    int i;
    for (i = 0; i < tmp.length; i++) {
        int line = i / brickOnLine;
        if (line == lineCnt)
            break;
        int posX = i % brickOnLine;
        double x = posX * brickLen;
        x = (line % 2 == 0) ? x : (x - (brickLen / 2));
        double y = (line) * brickHgt;
        p.setLocation(x, y);

        boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
        tmp[i] = b ? makeBrick(p, brickSize, typeA) : makeBrick(p, brickSize, typeB);
    }

    for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
        double x = (brickOnLine * brickLen) - (brickLen / 2);
        p.setLocation(x, y);
        tmp[i] = makeBrick(p, brickSize, typeA);
    }
    return tmp;
}

    //3 brick level
    private Brick[] makeTripleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB, int typeC){
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();

        int i;
        for (i = 0; i < tmp.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            if (b && ((line == 0) || (line == 1))) {
                tmp[i] = makeBrick(p, brickSize, typeA);
            }
            else if (!b && ((line == 0) || (line == 1))) {
                tmp[i] = makeBrick(p, brickSize, typeB);
            }
            else if(b && (line == 2)) {
                tmp[i] = makeBrick(p, brickSize, typeC);
            }
        }
        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            tmp[i] = makeBrick(p, brickSize, typeA);
        }
        return tmp;
    }

    //Create
    public Brick makeBrick(Point point, Dimension size, int type){
        return switch (type) {
            case CLAY -> new ClayBrick(point, size);
            case CEMENT -> new CementBrick(point, size);
            case ALUMINIUM -> new AluminiumBrick(point, size);
            case STEEL -> new SteelBrick(point, size);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
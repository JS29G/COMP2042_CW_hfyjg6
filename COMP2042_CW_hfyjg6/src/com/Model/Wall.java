/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;

public class Wall {

    public Ball ball;
    private Rectangle area;

    private Brick[] bricks;
    private Player player;
    private Levels levels;
    private Brick[][] brick_level;
    private int holder;
    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private int score;
    private String highScore = "";
    private boolean ballLost;

    //Create Wall
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);
        levels = new Levels();
        brick_level = levels.makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);

        holder = 0;
        this.score = 0;
        ballCount = 3;
        ball = new RubberBall(ballPos);
        initialiseSpeed();
        ballLost = false;

        //high score
        this.player = new Player((Point) ballPos.clone(),150,10, drawArea);
        if(highScore.equals(""))
        {
            highScore = this.getHighScore();
        }
        area = drawArea;
    }

    //Move
    public void move(){
        player.move();
        ball.move();
    }

    //Impact
    public void findImpacts(){
        if(player.impact(ball)){
            ball.reverseY();
        }
        else if(impactWall()){
            brickCount--;
            score++;
        }
        else if(impactBorder()) {
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }
    private boolean impactWall(){
        for(Brick b : bricks){
            //Vertical & Horizontal Impact
            switch (b.findImpact(ball)) {
                case Brick.UP_IMPACT -> {
                    ball.reverseY();
                    return b.setImpact(ball.getDown(), Crack.UP);
                }
                case Brick.DOWN_IMPACT -> {
                    ball.reverseY();
                    return b.setImpact(ball.getUp(), Crack.DOWN);
                }
                case Brick.LEFT_IMPACT -> {
                    ball.reverseX();
                    return b.setImpact(ball.getRight(), Crack.LEFT);
                }
                case Brick.RIGHT_IMPACT -> {
                    ball.reverseX();
                    return b.setImpact(ball.getLeft(), Crack.RIGHT);
                }
            }
        }
        return false;
    }
    private boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    //Highscore
    public String getHighScore(){
        FileReader readFile = null;
        BufferedReader reader = null;
        try {
            readFile = new FileReader("highscore.dat");
            reader = new BufferedReader(readFile);
            return reader.readLine();
        }
        catch (Exception e) {
            return "Mr Nobody:0";
        }
        finally {
            try{
                if(reader != null)
                    reader.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void checkScore(){
        if (highScore.equals(""))
        {
            return;
        }
        if(getScore() > Integer.parseInt((getHighScore().split(":")[1]))){
            String name = JOptionPane.showInputDialog("You've create a new High score! What is your name?");
            highScore = name + ":" + getScore();
            System.out.println(highScore);
            File scoreFile = new File("highscore.dat");
            if(!scoreFile.exists())
            {
                try {
                    scoreFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                FileWriter writer = new FileWriter(scoreFile);
                writer.write(this.highScore);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //Reset
    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
    }
    public void ballReset(){
        player.moveTo(startPoint);
        ball.moveTo(startPoint);

        initialiseSpeed();
        ballLost = false;
    }
    public void resetBallCount(){
        ballCount = 3;
    }

    public boolean ballEnd(){
        return ballCount == 0;
    }
    public boolean isDone(){
        return brickCount == 0;
    }

    public void nextLevel(){
        this.bricks = brick_level[holder++];
        this.brickCount = bricks.length;
        initialiseSpeed();
    }
    public boolean hasLevel(){
        return holder < brick_level.length;
    }

    //Location (speed)
    public void initialiseSpeed(){
        int speedX = 5, speedY = -2;
        ball.setSpeed(speedX,speedY);
    }
    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }
    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    public int getBrickCount(){
        return brickCount;
    }
    public int getBallCount(){
        return ballCount;
    }
    public boolean isBallLost(){
        return ballLost;
    }
    public Brick[] getBricks() {
        return bricks;
    }

    public Player getPlayer() {
        return player;
    }
    public int getScore() {
        return score;
    }
}


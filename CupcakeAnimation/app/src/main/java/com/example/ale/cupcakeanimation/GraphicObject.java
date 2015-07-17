package com.example.ale.cupcakeanimation;

import android.graphics.Bitmap;

import java.util.Random;


public class GraphicObject {
    private Bitmap cupcake;
    private Coordinates coordinates;
    private Speed speed;

    public GraphicObject(Bitmap bitmap){
        cupcake = bitmap;
        coordinates = new Coordinates();
        speed = new Speed();
    }

    public Bitmap getGraphic(){
        return cupcake;
    }
    public Coordinates getCoordinates(){
        return coordinates;
    }
    public Speed getSpeed(){return speed;}

    public class Coordinates{
        private int x;
        private int y;

        public Coordinates(){
            x = 0;
            y = 0;
        }

        public int getX() {
            return x + (cupcake.getWidth()/2);
        }

        public void setX(int value) {
            this.x = value - (cupcake.getWidth()/2);
        }

        public int getY() {
            return y + (cupcake.getHeight()/2);
        }

        public void setY(int value) {
            this.y = value - (cupcake.getHeight()/2);
        }

    }

    public class Speed{
        public static final int xDirectionRight = 1;
        public static final int xDirectionLeft = -1;
        public static final int yDirectionDown = 1;
        public static final int yDirectionUp = -1;

        private int x;
        private int y;
        private int xDirection;
        private int yDirection;

        public Speed(){
            x=2;
            y=2;
            xDirection = xDirectionRight;
            yDirection = yDirectionDown;
        }

        public int getXDirection() {
            return xDirection;
        }
        public void setXDirection(int direction) {
            xDirection = direction;
        }
        public void changeXDirection() {
            if (xDirection == xDirectionRight) {
                xDirection = xDirectionLeft;
            } else {
                xDirection = xDirectionRight;
            }
        }

        public int getYDirection() {
            return yDirection;
        }
        public void setYDirection(int direction) {
            yDirection = direction;
        }
        public void changeYDirection() {
            if (yDirection == yDirectionDown) {
                yDirection = yDirectionUp;
            } else {
                yDirection = yDirectionDown;
            }
        }

        public int getX(){return x;}
        public void setX(int speed){
            x=speed;
        }

        public int getY(){return y;}
        public void setY(int speed){
            y=speed;
        }
    }
}

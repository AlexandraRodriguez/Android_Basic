package com.example.ale.cupcakeanimation;

import android.graphics.Bitmap;


public class GraphicObject {
    private Bitmap cupcake;
    private Coordinates coordinates;

    public GraphicObject(Bitmap bitmap){
        cupcake = bitmap;
        coordinates = new Coordinates();
    }

    public Bitmap getGraphic(){
        return cupcake;
    }
    public Coordinates getCoordinates(){
        return coordinates;
    }

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

        public String getCoordinatesString(){
            return "Coordinates: ("+x+","+y+")";
        }
    }
}

package com.example.ale.groceryshoppingassistant;

public class Grid {

    private static final Integer[]data = {
            R.drawable.apple,R.drawable.orange,R.drawable.pineapple,R.drawable.papaya1,
            R.drawable.lemons,R.drawable.strawberries,R.drawable.watermelon,R.drawable.pears,
            R.drawable.peaches,R.drawable.grapes,R.drawable.banana1,R.drawable.lettuce,
            R.drawable.cucumber,R.drawable.carrots,R.drawable.tomato,R.drawable.cabbage,
            R.drawable.onions,R.drawable.avocado,R.drawable.broccoli,R.drawable.white_sugar,
            R.drawable.brown_sugar,R.drawable.salt,R.drawable.rice,R.drawable.potatoes,
            R.drawable.beans,R.drawable.chicken,R.drawable.beef1,R.drawable.pork1
    };

        private static final String[] names = new String[]{
                "apple","orange","pineapple","papaya","lemons","strawberries","watermelon","pears","peaches","grapes",
                "banana","lettuce","cucumber","carrots","tomato","cabbage","onions","avocado","broccoli",
                "white sugar","brown sugar","salt","rice","potatoes","beans","chicken","beef","pork"};

    public static Integer[] getData(){
        return data;
    }

        public static String getName(int pos){
                String res = "";
                if(pos<names.length){
                        res = names[pos];
                }
                return res;
        }


}

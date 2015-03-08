package com.example.loginface.loginface;


import android.content.res.Resources;

import java.util.List;
import java.util.Vector;

public class ShoppingCartHelper {

    public static final String PRODUCT_INDEX = "PRODUCT_INDEX";

    private static List<Product> catalog;
    private static List<Product> cart;

    public static List<Product> getCatalog(Resources res){
        if(catalog == null) {
            catalog = new Vector<>();
            catalog.add(new Product("Cafe", res
                    .getDrawable(R.drawable.cafe),
                        "El kilo de cafe en grano esta a $160.00", 160.00,  "#009688","0"));
            catalog.add(new Product("Brocoli", res
                    .getDrawable(R.drawable.brocoli),
                    "El kilo de brocoli esta a $19.50", 19.50,  "#B2DFDB","0"));
            catalog.add(new Product("Naranja", res
                    .getDrawable(R.drawable.citrico),
                    "El kilo de naranja esta a $9.90", 9.90,  "#009688","0"));
            catalog.add(new Product("Pescado", res
                    .getDrawable(R.drawable.carne),
                    "Filete del dia de pescado esta a $140.00", 14.99,  "#B2DFDB","0"));
            catalog.add(new Product("Carne", res
                    .getDrawable(R.drawable.carne),
                    "El kilo de carne esta a $140.00", 14.99,  "#009688","0"));
            catalog.add(new Product("Carne", res
                    .getDrawable(R.drawable.carne),
                    "El kilo de carne esta a $140.00", 14.99,  "#B2DFDB","0"));
            catalog.add(new Product("Carne", res
                    .getDrawable(R.drawable.carne),
                    "El kilo de carne esta a $140.00", 14.99,  "#009688","0"));
            catalog.add(new Product("Carne", res
                    .getDrawable(R.drawable.carne),
                    "El kilo de carne esta a $140.00", 14.99,  "#B2DFDB","0"));
        }

        return catalog;
    }

    public static List<Product> getCart() {
        if(cart == null) {
            cart = new Vector<>();
        }

        return cart;
    }

}

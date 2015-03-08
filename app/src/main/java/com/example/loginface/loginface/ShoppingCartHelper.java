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
            catalog.add(new Product("Jitomates", res
                    .getDrawable(R.drawable.jito),
                    "El kilo de jitomate esta a $30.00", 29.99));
            catalog.add(new Product("Aguacates", res
                    .getDrawable(R.drawable.aguacate),
                    "El kilo de aguacate esta a $25.00", 24.99));
            catalog.add(new Product("Carne", res
                    .getDrawable(R.drawable.carne),
                    "El kilo de carne esta a $140.00", 14.99));
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

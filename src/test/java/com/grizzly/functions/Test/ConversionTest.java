package com.grizzly.functions.Test;

import android.os.Bundle;
import com.grizzly.functions.BaseAndroidTestClass;
import com.grizzly.functions.Conversions;
import com.grizzly.functions.Test.DummyClasses.Cheese;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.*;

/**
 * Created by fpardo on 1/23/15.
 */
@RunWith(RobolectricTestRunner.class)
public class ConversionTest extends BaseAndroidTestClass {

    @Test
    public void testStringify(){

        Cheese cheese = new Cheese();
        cheese.setA(1);
        cheese.setB("b");

        System.out.println("Cheese as String:"+Conversions.stringifyObject(cheese));
    }

    @Test
    public void testBundlify(){

        Bundle bundle = new Bundle();
        bundle.putInt("a", 1);
        bundle.putString("b", "Fco");

        Cheese cheese = new Cheese();

        Conversions.bundleToObject(cheese, Cheese.class, bundle);
        cheese.printMe();
        Assert.assertTrue("We failed!", "Fco1".equals(cheese.getMe()));

    }

    @Test
    public void testGetAsMap(){

        List<Cheese> cheeses = new ArrayList<>();
        for(int c = 0; c<5;c++){
            Cheese cheese = new Cheese();
            cheese.setA(c);
            cheese.setB("Cheese number " + c);
            cheeses.add(cheese);
        }

        Map<Integer, Cheese> cheeseMap = Conversions.getDataAsMap(cheeses, "getA", Integer.class);
        Assert.assertTrue("We failed!", cheeseMap.containsKey(1));

    }

    @Test
    public void testCurrencies(){

        for(Currency currency : Currency.getAvailableCurrencies()){
            System.out.println(currency.getDisplayName() + " - " + currency.getCurrencyCode()
                    + " - "+ currency.getSymbol() + " - " + currency.getNumericCode()
                    + " - "+ currency.getDisplayName(Locale.forLanguageTag("es-CL"))
                    + " - "+ currency.getDisplayName(Locale.getDefault())
                    + " - "+ currency.getSymbol()
                    + " - "+ currency.getSymbol(Locale.getDefault())
            );

        }

    }

}

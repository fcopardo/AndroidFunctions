package com.grizzly.functions.Test;

import com.grizzly.functions.BaseAndroidTestClass;
import com.grizzly.functions.Conversions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by fpardo on 1/23/15.
 */
@RunWith(RobolectricTestRunner.class)
public class ConversionTest extends BaseAndroidTestClass {

    @Test
    public void testStringify(){

        class cheese{
            private int a = 1;
            private String b = "A";
        }
        System.out.println(Conversions.stringifyObject(new cheese()));
    }

}

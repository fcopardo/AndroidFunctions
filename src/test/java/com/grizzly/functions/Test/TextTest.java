package com.grizzly.functions.Test;

import com.grizzly.functions.BaseAndroidTestClass;
import com.grizzly.functions.R;
import com.grizzly.functions.TextFunctions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by fpardo on 1/23/15.
 */
@RunWith(RobolectricTestRunner.class)
public class TextTest extends BaseAndroidTestClass {

    @Test
    public void testFirstUCase(){

        org.junit.Assert.assertEquals("Hello there", TextFunctions.setFirstUCase("hello there"));

    }

    @Test
    public void testResourceGetter(){

        org.junit.Assert.assertEquals(getContext().getString(TextFunctions.getResourceId(R.string.class, "No")), "No");

    }


}

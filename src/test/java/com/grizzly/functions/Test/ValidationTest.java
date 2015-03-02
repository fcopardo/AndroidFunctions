package com.grizzly.functions.Test;

import com.grizzly.functions.BaseAndroidTestClass;
import com.grizzly.functions.TextFunctions;
import com.grizzly.functions.ValidationFunctions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by fpardo on 1/23/15.
 */
@RunWith(RobolectricTestRunner.class)
public class ValidationTest extends BaseAndroidTestClass {

    @Test
    public void testFullRut(){

        org.junit.Assert.assertEquals(true, ValidationFunctions.CheckRut("15.028.234-9"));
        org.junit.Assert.assertEquals(true, ValidationFunctions.CheckRut("14.138.418-k"));
        org.junit.Assert.assertEquals(true, ValidationFunctions.CheckRut("14.138.418-K"));
        org.junit.Assert.assertEquals(true, ValidationFunctions.CheckRut("14.558.972-K"));

    }

    @Test
    public void testPartialRut(){

        org.junit.Assert.assertEquals(true, ValidationFunctions.CheckRut("15028234-9"));
        org.junit.Assert.assertEquals(true, ValidationFunctions.CheckRut("14138418-k"));
        org.junit.Assert.assertEquals(true, ValidationFunctions.CheckRut("14138418-K"));

    }

    @Test
    public void testPlainRut(){

        org.junit.Assert.assertEquals(true, ValidationFunctions.CheckRut("150282349"));
        org.junit.Assert.assertEquals(true, ValidationFunctions.CheckRut("14138418k"));
        org.junit.Assert.assertEquals(true, ValidationFunctions.CheckRut("14138418K"));

    }

}

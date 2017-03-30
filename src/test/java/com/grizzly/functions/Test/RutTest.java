package com.grizzly.functions.Test;

import com.grizzly.functions.BaseAndroidTestClass;
import com.grizzly.functions.ValidationFunctions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by fpardo on 4/22/15.
 */
@RunWith(RobolectricTestRunner.class)
public class RutTest extends BaseAndroidTestClass {

    public RutTest(){
        super();
    }

    @Test
    public void testSimpleRutRegex() {

        org.junit.Assert.assertTrue("Oops!", "235519283".matches(ValidationFunctions.simpleRutPattern));

    }

    @Test
    public void testLineRutRegex() {

        org.junit.Assert.assertTrue("Oops!", "23551928-3".matches(ValidationFunctions.commonRutPattern));

    }

    @Test
    public void testFullRutRegex() {
        org.junit.Assert.assertTrue("Oops!", "23551928-3".matches(ValidationFunctions.commonRutPattern));

    }
}

package moanainc.com.moana;

import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;

import org.junit.Before;
import org.junit.Test;

import moanainc.com.moana.controller.RegisterActivity;

import static junit.framework.Assert.*;

/**
 * Created by darrion on 4/6/17.
 */

public class RegisterActivityValidatePasswordUnitTest {

    RegisterActivity registerActivity;
    String testPassword;

    @Before public void setup() {
        registerActivity = new RegisterActivity();
        testPassword = new String();
        if (registerActivity == null) {
            System.out.println("Register activity is null.");
        }
        if (testPassword == null) {
            System.out.println("Test password string is null.");
        }
    }

    @Test public void testLetterOnlyPassword() {
        // Test letters only password.
        testPassword = "abcdefgh"; // 8 alphabet characters
        assertEquals(false, registerActivity.validatePassword(testPassword));
    }

    @Test public void testNumberOnlyPassword() {
        // Test numbers only password.
        testPassword = "12345678"; // 8 numerical characters
        assertEquals(false, registerActivity.validatePassword(testPassword));
    }

    @Test public void testSymbolOnlyPassword() {
        // Test symbols only password.
        testPassword = "!@#$%^&*";
        assertEquals(false, registerActivity.validatePassword(testPassword));
    }

    @Test public void testEmptyStringPassword() {
        // Test password with no characters.
        testPassword = "";
        assertEquals(false, registerActivity.validatePassword(testPassword));
    }
}

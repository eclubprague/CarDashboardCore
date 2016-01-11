package com.eclubprague.cardashboard.core.utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Michael on 11.01.2016.
 */
public class StringValidatorTest {

    @Test
    public void testIsValid() throws Exception {
        String valid = "nic@a.cz";
        String invalid = "nic@cz";
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        assertTrue( StringValidator.isValid( valid, regex ) );
        assertFalse( StringValidator.isValid( invalid, regex ) );
    }

    @Test
    public void testIsEmail() throws Exception {
        String valid = "nic@a.cz";
        String invalid = "nic@cz";
        assertTrue( StringValidator.isEmail( valid ) );
        assertFalse( StringValidator.isEmail( invalid ) );
    }

    @Test
    public void testIsInteger() throws Exception {
        validate( Arrays.asList( "5", "1564674" ), Arrays.asList( "strouha", "6.8" ), new Unary() {
            @Override
            public boolean isValid( String input ) {
                return StringValidator.isInteger( input );
            }
        } );
    }

    @Test
    public void testIsDecimal() throws Exception {
        validate( Arrays.asList( "5", "15.8", "0.00000001" ), Arrays.asList( "strouha", "0x3" ), new Unary() {
            @Override
            public boolean isValid( String input ) {
                return StringValidator.isDecimal( input );
            }
        } );
    }

    @Test
    public void testIsEmpty() throws Exception {
        validate( Arrays.asList( "", null ), Arrays.asList( "strouha", "0x3" ), new Unary() {
            @Override
            public boolean isValid( String input ) {
                return StringValidator.isEmpty( input );
            }
        } );
    }

    @Test
    public void testIsLongEnough() throws Exception {
        assertTrue( StringValidator.isLongEnough( "123456",5 ) );
        assertFalse( StringValidator.isLongEnough( "", 5 ) );
        assertFalse( StringValidator.isLongEnough( "0x3", 5 ) );
    }

    private static void validate( List<String> validList, List<String> invalidList, Unary unary ) {
        for ( String valid : validList ) {
            assertTrue( unary.isValid( valid ) );
        }
        for ( String invalid : invalidList ) {
            assertFalse( unary.isValid( invalid ) );
        }
    }

    private static <T> void validate( List<String> validList, List<String> invalidList, Binary<T> binary, T data ) {
        for ( String valid : validList ) {
            assertTrue( binary.isValid( valid, data ) );
        }
        for ( String invalid : invalidList ) {
            assertFalse( binary.isValid( invalid, data ) );
        }
    }

    private interface Unary {
        boolean isValid( String input );
    }

    private interface Binary<T> {
        boolean isValid(String input, T data);
    }
}
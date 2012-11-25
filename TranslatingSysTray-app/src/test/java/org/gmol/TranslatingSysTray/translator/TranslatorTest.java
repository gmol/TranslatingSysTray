package org.gmol.TranslatingSysTray.translator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TranslatorTest extends TestCase {

        /**
         * Create the test case
         * 
         * @param testName
         *            name of the test case
         */
        public TranslatorTest(String testName) {
                super(testName);
        }

        /**
         * @return the suite of tests being tested
         */
        public static Test suite() {
                return new TestSuite(TranslatorTest.class);
        }

        /**
         * Rigourous Test :-)
         */
        public void testTranslator() {
                assertTrue(true);
        }
}

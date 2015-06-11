package com.atsebak.ui5.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SymbolExtractorTest {

    @Test
    public void testWordBasicExtract() throws Exception {
        CharSequence charSequence = "ahmad sebak";
        String extract = SymbolExtractor.extract(charSequence, 6);
        assertThat(extract, is("sebak"));
    }

    @Test
    public void testWithWordAtFrontExtract() {
        CharSequence charSequence = "ahmad sebak";
        String extract = SymbolExtractor.extract(charSequence, 1);
        assertThat(extract, is("ahmad"));
    }

    @Test
    public void testWithSpacesAtFrontExtract() {
        CharSequence charSequence = "\n\n\nahmad sebak";
        String extract = SymbolExtractor.extract(charSequence, 1);
        assertThat(extract, is(""));
    }
}
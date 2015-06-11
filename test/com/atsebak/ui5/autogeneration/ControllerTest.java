package com.atsebak.ui5.autogeneration;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class ControllerTest {

    @Test
    public void testGetAutogenerateCode() throws Exception {
        String controller = Controller.getAutogenerateCode("com.atsebak", "Main").replaceAll("^\\s+|\\s+$|\\s*(\n)\\s*|(\\s)\\s*", "$1$2").replace("\t", " ");
        String controllerName = controller.substring(0, Math.min(controller.length(), 37));
        assertEquals(controllerName, "sap.ui.controller('com.atsebak.Main',");
        assertThat(getFrequencyCharCount(controller, ';'), is(1));
        assertThat(getFrequencyCharCount(controller, ':'), is(4));
        assertThat(getFrequencyCharCount(controller, '{'), is(5));
        assertThat(getFrequencyCharCount(controller, '}'), is(5));
        assertThat(getFrequencyCharCount(controller, '}'), is(5));
        assertThat(StringUtils.countMatches(controller, "function()"), is(4));
        assertThat(StringUtils.countMatches(controller, "onInit:"), is(1));
        assertThat(StringUtils.countMatches(controller, "onBeforeRendering:"), is(1));
        assertThat(StringUtils.countMatches(controller, "onAfterRendering:"), is(1));
        assertThat(StringUtils.countMatches(controller, "onExit:"), is(1));
    }

    private Integer getFrequencyCharCount(String string, Character character) {
        Multiset<Character> chars = HashMultiset.create();
        for (int i = 0; i < string.length(); i++) {
            chars.add(string.charAt(i));
        }
        return chars.count(character);
    }
}

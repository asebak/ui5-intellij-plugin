package com.atsebak.ui5.projectbuilder;

import com.atsebak.ui5.autogeneration.JSView;
import org.junit.Test;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertThat;


public class ProjectPeerTest {

    @Test
    public void testGetSettings()  {
        ProjectPeer projectPeer = new ProjectPeer();
        UI5ProjectTemplateGenerator.UI5ProjectSettings settings = projectPeer.getSettings();
        assertThat(settings.getView(), instanceOf(JSView.class));
    }

}
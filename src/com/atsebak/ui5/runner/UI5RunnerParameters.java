package com.atsebak.ui5.runner;

import com.intellij.ide.browsers.WebBrowser;
import com.intellij.util.xmlb.annotations.Attribute;
import com.intellij.util.xmlb.annotations.Transient;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
public class UI5RunnerParameters implements Cloneable {
    private String url;
    private WebBrowser webBrowser;

    @Override
    protected UI5RunnerParameters clone() throws CloneNotSupportedException {
        try {
            return (UI5RunnerParameters) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}

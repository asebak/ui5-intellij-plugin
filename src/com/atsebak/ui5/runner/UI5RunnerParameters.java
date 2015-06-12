package com.atsebak.ui5.runner;

import com.intellij.ide.browsers.WebBrowser;
import lombok.Data;

@Data
public class UI5RunnerParameters implements Cloneable {
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

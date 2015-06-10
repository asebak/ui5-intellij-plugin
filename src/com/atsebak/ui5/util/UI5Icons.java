package com.atsebak.ui5.util;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public final class UI5Icons {
    /**
     * Get the Main UI5 Icon
     * @return
     */
    public static Icon getIcon(){
       return IconLoader.getIcon("/ui5.png", UI5Icons.class);
    }
}

package com.atsebak.ui5.Autogeneration;

/**
 * Created by asebak on 9/28/2014.
 */
public interface UI5View {
     String getExtension();
     String autogenerateCode(UI5Library ui5Library, String controllerPath);
}

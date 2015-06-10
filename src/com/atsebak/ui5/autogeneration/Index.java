package com.atsebak.ui5.autogeneration;

/**
 * Created by asebak on 9/28/2014.
 */
public class Index extends View {
    public String createIndexCode(UI5Library ui5Library, String rootModuleName, String intialViewExt){
        return codeGenerator.createIndexCode(ui5Library, rootModuleName, intialViewExt);
    }
}

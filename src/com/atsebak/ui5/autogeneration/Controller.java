package com.atsebak.ui5.autogeneration;

/**
 * Created by asebak on 9/28/2014.
 */
public class Controller {
    public static String getAutogenerateCode(String modulePath, String controllerName){
        return new CodeGenerator().createControllerCode(modulePath, controllerName);
    }
}

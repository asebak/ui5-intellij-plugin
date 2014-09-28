package Util;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

/**
 * Created by asebak on 9/28/2014.
 */
public class UI5Icons {
    public static Icon getIcon(){
       return IconLoader.getIcon("/Icons/ui5.png", UI5Icons.class);
    }
}

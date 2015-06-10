package com.atsebak.ui5.autogeneration;

import com.atsebak.ui5.config.UI5Library;

public interface UI5View {
     String getExtension();
     String autogenerateCode(UI5Library ui5Library, String controllerPath);
}

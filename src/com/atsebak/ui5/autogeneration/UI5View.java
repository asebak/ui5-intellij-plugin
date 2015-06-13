package com.atsebak.ui5.autogeneration;

import com.atsebak.ui5.AppType;

public interface UI5View {
     String getExtension();
     String autogenerateCode(AppType ui5Library, String controllerPath);
}

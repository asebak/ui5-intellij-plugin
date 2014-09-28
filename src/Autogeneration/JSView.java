package Autogeneration;

/**
 * Created by asebak on 9/28/2014.
 */
public class JSView extends View implements UI5View {
    @Override
    public String getExtension() {
        return "js";
    }

    @Override
    public String autogenerateCode(UI5Library ui5Library, String controllerPath) {
        return codeGenerator.createJavascriptViewCode(ui5Library, controllerPath);
    }

}

package Autogeneration;

/**
 * Created by asebak on 9/28/2014.
 * Uses String Builder instead of t4 like in vs
 */
class CodeGenerator {
    private StringBuilder sb;
    public String createControllerCode(String modulePath, String controllerName){
        String root = modulePath + "." + controllerName;
        sb = new StringBuilder("sap.ui.controller('");
        sb.append(root).append("', {").append("    \n     /**\n" +
                "    * Called when a controller is instantiated and its View controls (if available) are already created.\n" +
                "    * Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.\n" +
                "    * @memberOf ").append(root).append(" **/  \n   onInit: function() {\n" +
                "\n" +
                "    },").append("    /**\n" +
                "    * Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered\n" +
                "    * (NOT before the first rendering! onInit() is used for that one!).\n" +
                "    * @memberOf ").append(root).append(" \n**/  \n   onBeforeRendering: function() {\n" +
                "\n" +
                "    },\n").append("    /**\n" +
                "    * Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.\n" +
                "    * This hook is the same one that SAPUI5 controls get after being rendered.\n" +
                "    * @memberOf ").append(root).append(" **/  \n   onAfterRendering: function() {\n" +
                "\n" +
                "    },").append("    /**\n" +
                "    * Called when the Controller is destroyed. Use this one to free resources and finalize activities.\n" +
                "    * @memberOf ").append(root).append("  \n**/  \n  onExit: function() {\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "});");
        return  sb.toString();
    }

    public String createIndexCode(UI5Library ui5Library, String rootModuleName, String intialViewExt){
        sb = new StringBuilder();
        sb.append("<!DOCTYPE HTML>\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "\t\t<meta http-equiv='Content-Type' content='text/html;charset=UTF-8'/>");
        switch (ui5Library){
            case Desktop:
                sb.append("\n<script src=\"https://openui5.hana.ondemand.com/resources/sap-ui-core.js\"\n" +
                        "\t\t\t\tid=\"sap-ui-bootstrap\"\n" +
                        "\t\t\t\tdata-sap-ui-libs=\"sap.ui.commons,sap.ui.table,sap.ui.ux3\"\n" +
                        "\t\t\t\tdata-sap-ui-theme=\"sap_bluecrystal\">\n" +
                        "\t\t</script>");
                sb.append("\n<script>\n" +
                        "\t\t\t\tsap.ui.localResources(\"").append(rootModuleName).append("\");\n")
                        .append("\t\t\t\tsap.ui.localResources(\"util\");\n" +
                        "\t\t\t\tsap.ui.localResources(\"i18n\");\n" +
                        "\t\t\t\tvar view = sap.ui.view({viewName:\"").append(rootModuleName).append(".Main\"")
                        .append(", type:sap.ui.core.mvc.ViewType.").append(intialViewExt).append("});\n" +
                        "\t\t\t\tview.placeAt(\"content\");\n" +
                        "\t\t</script>");
                break;
            case Mobile:
                sb.append("\n<script src=\"https://openui5.hana.ondemand.com/resources/sap-ui-core.js\"\n" +
                        "\t\t\t\tid=\"sap-ui-bootstrap\"\n" +
                        "\t\t\t\tdata-sap-ui-libs=\"sap.m\"\n" +
                        "\t\t\t\tdata-sap-ui-theme=\"sap_bluecrystal\">\n" +
                        "\t\t</script>");
                sb.append("\n<script>\n" +
                        "\t\t\t\tsap.ui.localResources(\"").append(rootModuleName).append("\");\n" +
                        "\t\t\t\tsap.ui.localResources(\"util\");\n" +
                        "\t\t\t\tsap.ui.localResources(\"i18n\");\n" +
                        "\t\t\t\tvar app = new sap.m.App({initialPage:\"app\"});\n" +
                        "\t\t\t\tvar page = sap.ui.view({id:\"app\", viewName:\"").append(rootModuleName).append(".Main\", type:sap.ui.core.mvc.ViewType.")
                        .append(intialViewExt).append("});\n" +
                        "\t\t\t\tapp.addPage(page);\n" +
                        "\t\t\t\tapp.placeAt(\"content\");\n" +
                        "\t\t</script>");
                break;
        }
        sb.append("\n</head>\n" +
                "\t<body class=\"sapUiBody\" role=\"application\">\n" +
                "\t\t<div id=\"content\"></div>\n" +
                "\t</body>\n" +
                "</html>");
        return sb.toString();
    }

    public String createHtmlViewCode(UI5Library ui5Library, String controllerPath){
        sb = new StringBuilder();
        sb.append("<template data-controller-name='").append(controllerPath).append("'>");
        switch (ui5Library){
            case Desktop:
                break;
            case Mobile:
                sb.append("\t<div data-sap-ui-type=\"sap.m.Page\" data-title=\"Title\">\n" +
                        "\t\t\n<div data-sap-ui-aggregation=\"content\">\n" +
                        "\t\t\n" +
                        "\t\t</div>\n" +
                        "\t</div>\n");
                break;
        }
        sb.append("\n</template>\n");
        return sb.toString();
    }

    public String createJavascriptViewCode(UI5Library ui5Library, String controllerPath){
        sb = new StringBuilder();
        sb.append("sap.ui.jsview(\"").append(controllerPath).append("\", {")
                .append("\n/** Specifies the Controller belonging to this View. \n" +
                        "\t* In the case that it is not implemented, or that \"null\" is returned, this View does not have a Controller.\n" +
                        "\t* @memberOf ").append(controllerPath)
                .append("**/ \n\tgetControllerName : function() {\n" +
                        "\t\treturn '").append(controllerPath).append("';").append("\n},")
                .append("\n/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. \n" +
                        "\t* Since the Controller is given to this method, its event handlers can be attached right away. \n" +
                        "\t* @memberOf ").append(controllerPath)
                .append("**/ \n \tcreateContent : function(oController) {\n");
        switch (ui5Library){
            case Desktop:
                break;
            case Mobile:
                sb.append(" \t\treturn new sap.m.Page({\n" +
                        "\t\t\ttitle: \"Title\",\n" +
                        "\t\t\tcontent: [\n" +
                        "\t\t\t\n" +
                        "\t\t\t]\n" +
                        "\t\t});\n");
                break;
        }
        sb.append("}\n" +
                "\n" +
                "});");
        return sb.toString();
    }

    public String createXmlViewCode(UI5Library ui5Library, String controllerPath) {
        sb = new StringBuilder();
        String xmlns;
        switch (ui5Library){
            case Desktop:
                sb.append("<core:View xmlns:core=\"sap.ui.core\" xmlns:mvc=\"sap.ui.core.mvc\" xmlns=\"sap.ui.commons\"")
                        .append(" controllerName='").append(controllerPath).append("'").append(" xmlns:html=\"http://www.w3.org/1999/xhtml\">\n" +
                        "</core:View>");
                break;
            case Mobile:
                sb.append("<core:View xmlns:core=\"sap.ui.core\" xmlns:mvc=\"sap.ui.core.mvc\" xmlns=\"sap.m\"")
                        .append(" controllerName='").append(controllerPath).append("'").append(" xmlns:html=\"http://www.w3.org/1999/xhtml\">\n" +
                        "\t<Page title=\"Title\">\n" +
                        "\t\t<content>\n" +
                        "\t\n" +
                        "\t\t</content>\n" +
                        "\t</Page>\n" +
                        "</core:View>");
                break;
        }
        return sb.toString();
    }

    public String createJsonViewCode(UI5Library ui5Library, String controllerPath){
        sb = new StringBuilder();
        sb.append("{\n" +
                "\t\"Type\":\"sap.ui.core.mvc.JSONView\",\n" +
                "\t\"controllerName\":\"").append(controllerPath).append(",\t\"content\": [{");
        switch (ui5Library){
            case Desktop:
                break;
            case Mobile:
                sb.append("\t\"Type\":\"sap.m.Page\",\n" +
                        "\t\t\"title\":\"Title\",\n" +
                        "\t\t\"content\":[\n" +
                        "\t\t\n" +
                        "\t\t]");
                break;
        }
        sb.append("\t}]\n" +
                "}");
        return sb.toString();
    }
}

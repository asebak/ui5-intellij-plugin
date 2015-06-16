<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv='Content-Type' content='text/html;charset=UTF-8'/>
    <link rel="stylesheet" type="text/css" href="css/${rootModuleName}.css">
    <script src="resources/sap-ui-core.js" id="sap-ui-bootstrap" data-sap-ui-libs="sap.ui.commons,sap.ui.table,sap.ui.ux3" data-sap-ui-theme="sap_goldreflection">
    </script>
    <script>
        sap.ui.localResources("${rootModuleName}");
        sap.ui.localResources("util");
        sap.ui.localResources("i18n");
        var oView = sap.ui.view({
            viewName: "${rootModuleName}.Main",
            type: sap.ui.core.mvc.ViewType.${intialViewExt}
        });
        oView.placeAt("content");
    </script>
</head>
<body class="sapUiBody" role="application">
    <div id="content">
    </div>
</body>
</html>

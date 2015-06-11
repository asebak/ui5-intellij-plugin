<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv='Content-Type' content='text/html;charset=UTF-8'/>
    <script src="https://openui5.hana.ondemand.com/resources/sap-ui-core.js" id="sap-ui-bootstrap"
            data-sap-ui-libs="sap.m" data-sap-ui-theme="sap_bluecrystal">
    </script>
    <script>
        sap.ui.localResources("${rootModuleName}");
        sap.ui.localResources("util");
        sap.ui.localResources("i18n");
        var app = new sap.m.App({initialPage: "app"});
        var page = sap.ui.view({
            id: "app",
            viewName: "${rootModuleName}.Main",
            type: sap.ui.core.mvc.ViewType.${intialViewExt} });
        app.addPage(page);
        app.placeAt("content");
    </script>
</head>
<body class="sapUiBody" role="application">
<div id="content">

</div>
</body>
</html>

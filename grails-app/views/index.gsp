<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Agency Map</title>
</head>
<body>
<!--
    <div class="svg" role="presentation">
        <div class="grails-logo-container">
            <asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>
        </div>
    </div>
-->
    <div id="content" role="main">
        <section class="row colset-2-its">
            <h1>Geolocation Agency Map</h1>

            <div id="controllers" role="navigation">

                <h2>Available Controllers:</h2>
                <ul>
                    <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                        <li class="controller">
                            <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
                        </li>
                    </g:each>
                </ul>

            </div>
        </section>
    </div>

</body>
</html>

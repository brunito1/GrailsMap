<%--
  Created by IntelliJ IDEA.
  User: bbergoglio
  Date: 01/03/2018
  Time: 11:25
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Agency Map</title>
</head>

<body>
<div id="content" role="main">
    <section class="row colset-2-its">
        <h1>Geolocation Agency Map</h1>

        <p>
            Para utilizar la plataforma debe ingresar una dirección, elegir un medio de pago e ingresar un radio de búsqueda en metros
        </p>
        <br/>

        <g:if test="${flash.error}">
            <div class="alert alert-danger" style="display: block">${flash.error}</div>
        </g:if>
        <g:if test="${flash.success}">
            <div class="alert alert-success" style="display: block">${flash.success}</div>
        </g:if>

        <g:form action="agencyList" method="POST">
            <div>
                <div class="col-md-12">
                    <div class="col-md-4">
                        <h3>Dirección: </h3>
                    </div>
                    <div class="col-md-4">
                        <g:textField placeholder="Dirección" name="address"/>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="col-md-4">
                        <h3>Método de pago: </h3>
                    </div>
                    <div class="col-md-4">
                        <g:select id="paymentMethods"
                                  name="paymentMethods"
                                  from="${ticketPaymentMethods}"
                                  optionKey="${{it.id}}"
                                  optionValue="${{it.name}}"/>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="col-md-4">
                        <h3>Radio de búsqueda: </h3>
                    </div>
                    <div class="col-md-4">
                        <g:textField placeholder="Radio de búsqueda" name="radius"/>
                    </div>
                </div>
                <fieldset class="buttons" style="offset-top: 40px;">
                    <g:submitButton name="create" class="save" value="Buscar" />
                </fieldset>
            </div>
        </g:form>

    </section>
</div>
</body>
</html>
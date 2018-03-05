<%--
  Created by IntelliJ IDEA.
  User: bbergoglio
  Date: 01/03/2018
  Time: 13:02
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Agencias</title>

    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/responsive/2.2.1/css/responsive.bootstrap.min.css">


    <script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>

    <style>
    #map {
        height: 400px;
        width: 100%;
    }
    </style>
</head>

<body>
<div id="content" role="main">
    <section class="row colset-2-its">
        <table id="agenciesTable" class="table table-bordered" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>Descripción</th>
                <th>Distancia</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${agenciesList.results}" var="agency">
                <tr onClick="ShowModal('${agency.description}','${agency.address.address_line}','${agency.address.city}','${agency.address.country}','${agency.address.location}','${agency.address.other_info}','${agency.address.state}','${agency.address.zip_code}')" data-toggle="modal" data-address-line="${agency.address_line}" data-id="${agency}" data-target="#myModal">
                    <td>${agency.description}</td>
                    <td>${agency.distance}</td>
                </tr>
            </g:each>
            </tbody>
        </table>


    </section>
</div>

<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title"><span id="myAgencyName"></span></h3>

            </div>
            <div class="modal-body edit-content">

                <h5>
                    Dirección: <span id="myAddress"></span><br/>
                    Ciudad: <span id="myCity"></span><br/>
                    País: <span id="myCountry"></span><br/>
                    Localización Geográfica: <span id="myLocation"></span><br/>
                    Otra Información: <span id="anotherInfo"></span><br/>
                    Estado: <span id="myState"></span><br/>
                    Código Postal: <span id="myPostalCode"></span><br/>
                </h5>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default " data-dismiss="modal">Aceptar</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<script type="text/javascript">
    function ShowModal(agencyName,address_line, city, country, location, other_info, state, zip_code){
        document.getElementById("myAgencyName").innerHTML = agencyName;
        document.getElementById("myAddress").innerHTML = address_line;
        document.getElementById("myCity").innerHTML = city;
        document.getElementById("myCountry").innerHTML = country;
        document.getElementById("myLocation").innerHTML = location;
        document.getElementById("anotherInfo").innerHTML = other_info;
        document.getElementById("myState").innerHTML = state;
        document.getElementById("myPostalCode").innerHTML = zip_code;
    }
</script>
<!--
<script>
    <g:applyCodec encodeAs="none">
        list = ${agenciesList.results};
    </g:applyCodec>
</script>
-->
<div id="map"></div>
<script>
    function initMap() {

        var myMarkers = [];

        <g:each in="${agenciesList.results}" var="myAgency">
            var marks = ['${myAgency.description}', "${myAgency.address.location}".split(",")[0], "${myAgency.address.location}".split(",")[1]];
            myMarkers.push(marks);
        </g:each>
        console.log(myMarkers);
        console.log('${initialLatitude}');
        console.log('${initialLongitude}');

        var initPosition = {lat: Number('${initialLatitude}'), lng: Number('${initialLongitude}')};
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 14,
            center: initPosition
        });
        for(i = 0 ; i<myMarkers.length ; i++){
            var position = {lat: Number(myMarkers[i][1]), lng: Number(myMarkers[i][2])};
            var marker = new google.maps.Marker({
                position: position,
                map: map
            });
        }

    }
</script>


<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAS-Q4EgkIKs4OsGQNcb7aiu4i0KKo5yjw&callback=initMap">
</script>
</body>
</html>
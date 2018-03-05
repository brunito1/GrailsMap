package com.itacademy.grailsmap.services

import com.itacademy.grailsmap.Marker
import com.itacademy.grailsmap.PaymentMethod
import exceptions.CantConnectToApi
import exceptions.IncompleteFieldsException
import exceptions.InvalidRadius
import grails.gorm.transactions.Transactional
import grails.web.servlet.mvc.GrailsParameterMap
import groovy.json.JsonSlurper

import java.text.NumberFormat

@Transactional
class WelcomeService {

    List<PaymentMethod> getTicketPaymentMethods() {
        def url = new URL('https://api.mercadolibre.com/sites/MLA/payment_methods')
        def connection = (HttpURLConnection) url.openConnection()
        connection.setRequestMethod("GET")
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("User-Agent", "Mozilla/5.0")
        if (connection == null) {
            throw new CantConnectToApi("Error al conectarse al servidor")
        }
        JsonSlurper json = new JsonSlurper()

        List<PaymentMethod> paymentMethods = json.parse(connection.getInputStream())
        List<PaymentMethod> ticketPM = new ArrayList<PaymentMethod>()

        for (PaymentMethod pm : paymentMethods) {
            if (pm.getPayment_type_id() == "ticket" || pm.getPayment_type_id() == "atm") {
                ticketPM.add(pm)
            }
        }

        return ticketPM
    }


    def getAgencies(GrailsParameterMap params) {
        String paymentMethod = params.get("paymentMethods")
        String addressToSearch = params.get("address")
        String radius = params.get("radius")

        if (paymentMethod == null || paymentMethod.isEmpty() || addressToSearch == null || addressToSearch.isEmpty() || radius == null || radius.isEmpty()) {
            throw new IncompleteFieldsException("Debe completar todos los campos")
        }
        System.out.println(paymentMethod + " " + addressToSearch + " " + radius)

        addressToSearch = URLEncoder.encode(addressToSearch, "UTF-8")

        def url = new URL('https://maps.googleapis.com/maps/api/geocode/json?address=' + addressToSearch + '&key=AIzaSyAS-Q4EgkIKs4OsGQNcb7aiu4i0KKo5yjw')
        def connection = (HttpURLConnection) url.openConnection()
        connection.setRequestMethod("GET")
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("User-Agent", "Mozilla/5.0")
        if (connection == null) {
            throw new CantConnectToApi("Error al conectarse al servidor")
        }
        JsonSlurper json = new JsonSlurper()

        def mySearchValue = json.parse(connection.getInputStream())

        def latitude = mySearchValue.results.geometry.location.lat
        def longitude = mySearchValue.results.geometry.location.lng

        Marker.getAll().each { it.delete() }
        Marker myMarker = new Marker()
        myMarker.latitude = latitude[0]
        myMarker.longitude = longitude[0]
        myMarker.save()

        System.out.println("latitude: " + latitude[0] + " longitude: " + longitude[0])
        /*
        try{
            radius = Integer.parseInt(radius);
        }catch(NumberFormatException ex){
            throw new InvalidRadius("Error al ingresar el radio")
        }
        */

        def agenciesURL = new URL('https://api.mercadolibre.com/sites/MLA/payment_methods/' + paymentMethod + '/agencies?near_to=' + latitude[0] + ',' + longitude[0] + ',' + radius + '&limit=10&offset=0')
        def agenciesConnection = (HttpURLConnection) agenciesURL.openConnection()
        agenciesConnection.setRequestMethod("GET")
        agenciesConnection.setRequestProperty("Accept", "application/json")
        agenciesConnection.setRequestProperty("User-Agent", "Mozilla/5.0")
        if (agenciesConnection == null) {
            throw new CantConnectToApi("Error al conectarse al servidor")
        }
        def myAgencies = json.parse(agenciesConnection.getInputStream())
        System.out.println(myAgencies)
        return myAgencies

    }

    def getLatitude() {
        for (Marker myMarker : Marker.getAll()) {
            return myMarker.getLatitude()
        }
    }

    def getLongitude() {
        for (Marker myMarker : Marker.getAll()) {
            return myMarker.getLongitude()
        }
    }
}

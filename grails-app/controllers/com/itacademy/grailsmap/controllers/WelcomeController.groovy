package com.itacademy.grailsmap.controllers

import com.itacademy.grailsmap.Marker
import com.itacademy.grailsmap.PaymentMethod
import com.itacademy.grailsmap.services.WelcomeService
import exceptions.CantConnectToApi
import exceptions.IncompleteFieldsException
import exceptions.InvalidRadius

class WelcomeController {

    static allowedMethods = [agencyList: "POST"]

    WelcomeService welcomeService


    def index() {
        try {
            List<PaymentMethod> paymentMethods = welcomeService.getTicketPaymentMethods()
            render(view: "index", model: [ticketPaymentMethods: paymentMethods])
        } catch (CantConnectToApi ex) {
            flash.error = ex.getMessage()
            render(view: "index")
        } catch (Exception ex) {
            ex.printStackTrace()
            flash.error = 'No se pudo realizar su solicitud.'
            redirect action: 'index'
        }
    }

    def agencyList() {
        try {
            def myAgencies = welcomeService.getAgencies(params)
            def latitude = welcomeService.getLatitude()
            def longitude = welcomeService.getLongitude()
            render(view: "agencyList", model: [agenciesList: myAgencies, initialLatitude: latitude, initialLongitude: longitude])
        } catch (CantConnectToApi ex) {
            ex.printStackTrace()
            flash.error = ex.getMessage()
            render(view: "index")
        } catch (IncompleteFieldsException ex) {
            ex.printStackTrace()
            flash.error = ex.getMessage()
            redirect action: 'index'
        } catch (InvalidRadius ex) {
            ex.printStackTrace()
            flash.error = ex.getMessage()
            redirect action: 'index'
        } catch (Exception ex) {
            ex.printStackTrace()
            flash.error = 'No se pudo realizar su solicitud.'
            redirect action: 'index'
        }
    }

}

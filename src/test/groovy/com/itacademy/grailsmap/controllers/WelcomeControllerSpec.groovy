package com.itacademy.grailsmap.controllers

import com.itacademy.grailsmap.PaymentMethod
import com.itacademy.grailsmap.services.WelcomeService
import grails.testing.gorm.DomainUnitTest
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class WelcomeControllerSpec extends Specification implements ControllerUnitTest<WelcomeController>, DomainUnitTest<PaymentMethod> {

    def setup() {
    }

    def cleanup() {
    }

    void "when agencyList is called, agencyList view is rendered"() {
        when:
        controller.agencyList()
        then:
        view == "/welcome/agencyList.gsp"
    }

    void "Test the index action returns the correct model"() {
        given:
        controller.welcomeService = Mock(WelcomeService) {
            getTicketPaymentMethods() >> ["first","second"]
        }

        when: "when index method is called"
        controller.index()

        then: "the index view is called"
        view == "/welcome/index"
        !model.isEmpty()
        model.ticketPaymentMethods != null
    }
/*
    void "Test the agencyList action returns the correct model"() {
        given:
        controller.welcomeService = Mock(WelcomeService) {
            controller.params.paymentMethods = 'efectivo'
            controller.params.address = 'dada'
            controller.params.radius = 'dada'
            getAgencies(controller.params) >> ["first","second"]
        }

        when: "when agencyList method is called"
        controller.index()

        then: "the agencyList view is called"
        !model.isEmpty()
        model.agenciesList != null
        model.initialLatitude != null
        model.initialLongitude != null
    }
*/
}

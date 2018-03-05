package com.itacademy.grailsmap.services

import com.itacademy.grailsmap.Marker
import com.itacademy.grailsmap.PaymentMethod
import grails.testing.gorm.DomainUnitTest
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class WelcomeServiceSpec extends Specification implements ServiceUnitTest<WelcomeService>, DomainUnitTest<Marker> {

    def setup() {
    }

    def cleanup() {
    }

    /*
    void "Test the getTicketPaymentMethods action returns the correct model"() {
        given: "list of payment methods"
        List<PaymentMethod> allmethods = service.getTicketPaymentMethods()

        when: "when I make the relation"
        def commons = PaymentMethod.getAll().asList().intersect(allmethods)
        def difference = PaymentMethod.getAll().asList().plus(allmethods)
        difference.removeAll(commons)

        then: "the ticket payment methods are returned"
        PaymentMethod.getAll() == difference
    }
    */

    void "Test the getLatitude action returns the correct number"() {
        setup: "list of payment methods"
        Marker marker = new Marker()
        marker.latitude = '10'
        marker.longitude = '20'
        marker.save()

        when: "when getLatitude method is called"
        def myLatitude = service.getLatitude()

        then: "the latitude is returned"
        marker.latitude == myLatitude
    }

    void "Test the getLongitude action returns the correct number"() {
        setup: "list of payment methods"
        Marker marker = new Marker()
        marker.latitude = '10'
        marker.longitude = '20'
        marker.save()

        when: "when getLatitude method is called"
        def myLongitude = service.getLongitude()

        then: "the latitude is returned"
        marker.longitude == myLongitude
    }
}

package com.itacademy.grailsmap

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertTrue

class MarkerSpec extends Specification implements DomainUnitTest<Marker> {

    def setup() {
    }

    def cleanup() {
    }

    void "test to create a new Marker"() {
        setup:
        Marker marker = new Marker(latitude: '-10.222222', longitude: "-41.33333").save()
        Marker anotherMarker = new Marker()
        anotherMarker.latitude = '22.394394'
        anotherMarker.longitude = '413.388318'
        anotherMarker.save()
        expect:
        marker.getLatitude() == '-10.222222'
        marker.getLongitude() == '-41.33333'
        Marker.count == 2
    }

}

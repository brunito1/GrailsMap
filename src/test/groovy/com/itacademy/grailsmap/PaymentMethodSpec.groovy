package com.itacademy.grailsmap

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class PaymentMethodSpec extends Specification implements DomainUnitTest<PaymentMethod> {

    def setup() {
    }

    def cleanup() {
    }

    void "test to create a new PaymentMethod"() {
        setup:
        new PaymentMethod(id: 'mercadopago', name: 'Mercado Pago', payment_type_id: '1').save()
        PaymentMethod anotherPaymentMethod = new PaymentMethod()
        anotherPaymentMethod.id = 'otro'
        anotherPaymentMethod.name = 'otro'
        anotherPaymentMethod.payment_type_id = '2'
        anotherPaymentMethod.save()
        expect:
        PaymentMethod.count == 2
    }

    void "test to check if a payment method can be saved without id, name or payment_type_id"() {
        setup:
        PaymentMethod paymentMethod = new PaymentMethod()
        paymentMethod.id = 'mercadopago'
        paymentMethod.name = 'Mercado Pago'
        paymentMethod.payment_type_id = '1'
        paymentMethod.save()

        PaymentMethod withoutId = new PaymentMethod()
        withoutId.id = null
        withoutId.name = 'Mercado Pago'
        withoutId.payment_type_id = '1'
        withoutId.save()

        PaymentMethod withoutName = new PaymentMethod()
        withoutName.id = 'mercadopago'
        withoutName.name = null
        withoutName.payment_type_id = '1'
        withoutName.save()

        PaymentMethod withoutPaymentTypeId = new PaymentMethod()
        withoutPaymentTypeId.id = 'mercadopago'
        withoutPaymentTypeId.name = 'Mercado Pago'
        withoutPaymentTypeId.payment_type_id = null
        withoutPaymentTypeId.save()

        expect:
        PaymentMethod.count() == 1
    }
}

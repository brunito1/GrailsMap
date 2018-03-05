package com.itacademy.grailsmap

class PaymentMethod {

    String id
    String name
    String payment_type_id

    String getId() {
        return id
    }

    void setId(String id) {
        this.id = id
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getPayment_type_id() {
        return payment_type_id
    }

    void setPayment_type_id(String payment_type_id) {
        this.payment_type_id = payment_type_id
    }

    static constraints = {
        id nullable: false, blank: false
        name nullable: false, blank: false
        payment_type_id nullable: false, blank: false
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", payment_type_id='" + payment_type_id + '\'' +
                '}';
    }
}

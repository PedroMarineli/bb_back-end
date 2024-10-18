//package br.com.fatec.burguerboss.payment;
//
//import jakarta.persistence.*;
//
//@Entity
//public class Payment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private String description;
//    @Enumerated(EnumType.STRING)
//    private PaymentMethod paymentMethod;
//
//    public Payment(DataListPayment data) {
//        this.id = data.id();
//        this.description = data.description();
//        this.paymentMethod = data.paymentMethod();
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public PaymentMethod getPaymentMethod() {
//        return paymentMethod;
//    }
//
//    public void setPaymentMethod(PaymentMethod paymentMethod) {
//        this.paymentMethod = paymentMethod;
//
//    }
//}

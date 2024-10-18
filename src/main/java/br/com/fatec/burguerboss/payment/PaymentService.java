//package br.com.fatec.burguerboss.payment;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class PaymentService {
//    @Autowired
//    private PaymentRepository repositoryPayment;
//
//    public List<DataListPayment> listPayment() {
//        return repositoryPayment.findAll().stream().map(DataListPayment::new).collect(Collectors.toList());
//    }
//}

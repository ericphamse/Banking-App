package com.tunglam.banking.payment_history;

import com.tunglam.banking.user.User;
import com.tunglam.banking.user.UserRepository;
import com.tunglam.banking.payment_history.dto.Payment;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;
    private final UserRepository userRepository;

    public PaymentHistoryService(PaymentHistoryRepository paymentHistoryRepository,
                                  UserRepository userRepository) {
        this.paymentHistoryRepository = paymentHistoryRepository;
        this.userRepository = userRepository;
    }

    public List<Payment> getPaymentHistoryForUser(String email ) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
        Long userId = user.getId();
        
        return paymentHistoryRepository.findByUserIdOrderByPaymentDateDesc(userId)
            .stream()
            .map(p -> new Payment(
                p.getId(),
                p.getInvoiceId(),
                p.getAmount(),
                p.getStatus(),
                p.getBillingPlan() != null ? p.getBillingPlan() : "",
                p.getPaymentDate() != null ? p.getPaymentDate().toString() : ""
            ))
            .collect(java.util.stream.Collectors.toList());
    }

    // ADD a new payment
    /**public PaymentHistory addPayment(String email, PaymentHistory payment) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        payment.setUser(user);
        return paymentHistoryRepository.save(payment);
    }

    // UPDATE payment status
    public PaymentHistory updateStatus(String invoiceId, PaymentStatus status) {
        PaymentHistory payment = paymentHistoryRepository.findByInvoiceId(invoiceId);
        payment.setStatus(status);
        return paymentHistoryRepository.save(payment);
    }

    // DELETE a payment
    public void deletePayment(Long id) {
        paymentHistoryRepository.deleteById(id);
    }**/
}
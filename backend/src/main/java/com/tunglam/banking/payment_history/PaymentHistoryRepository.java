package com.tunglam.banking.payment_history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {

    List<PaymentHistory> findByUserIdOrderByPaymentDateDesc(Long userId);

    List<PaymentHistory> findByUserIdAndStatus(Long userId, PaymentStatus status);

    PaymentHistory findByInvoiceId(String invoiceId);
}
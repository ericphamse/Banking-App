package com.tunglam.banking.payment_history.dto;

import com.tunglam.banking.payment_history.PaymentStatus;

public class Payment {
    private Long id;
    private String invoiceId;
    private Double amount;
    private PaymentStatus status;
    private String billingPlan;
    private String paymentDate;

    public Payment() {}

    public Payment(Long id, String invoiceId, Double amount, PaymentStatus status, String billingPlan, String paymentDate) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.amount = amount;
        this.status = status;
        this.billingPlan = billingPlan;
        this.paymentDate = paymentDate;
    }

    public Long getId() { return id; }
    public String getInvoiceId() { return invoiceId; }
    public Double getAmount() { return amount; }
    public PaymentStatus getStatus() { return status; }
    public String getBillingPlan() { return billingPlan; }
    public String getPaymentDate() { return paymentDate; }
}
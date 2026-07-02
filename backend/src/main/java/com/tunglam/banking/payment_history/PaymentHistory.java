package com.tunglam.banking.payment_history;

import com.tunglam.banking.user.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_history")
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false, length = 20)
    private PaymentStatus status;

    @Column(name = "recipient_name", length = 255)
    private String recipientName;

    @Column(name = "payment_method", length = 50)
    private String paymentMethod;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "invoice_id", length = 50)
    private String invoiceId;

    @Column(name = "billing_plan", length = 100)
    private String billingPlan;


    public Long getId() { return id; }

    public User getUser() { return user; }

    public Double getAmount() { return amount; }

    public PaymentStatus getStatus() { return status; }

    public String getRecipientName() { return recipientName; }

    public String getPaymentMethod() { return paymentMethod; }

    public LocalDateTime getPaymentDate() { return paymentDate; }

    public String getInvoiceId() { return invoiceId; }

    public String getBillingPlan() { return billingPlan; }

    public void setId(Long id) { this.id = id; }

    public void setUser(User user) { this.user = user; }

    public void setAmount(Double amount) { this.amount = amount; }

    public void setStatus(PaymentStatus status) { this.status = status; }

    public void setRecipientName(String recipientName) { this.recipientName = recipientName; }

    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }

    public void setInvoiceId(String invoiceId) { this.invoiceId = invoiceId; }

    public void setBillingPlan(String billingPlan) { this.billingPlan = billingPlan; }
}
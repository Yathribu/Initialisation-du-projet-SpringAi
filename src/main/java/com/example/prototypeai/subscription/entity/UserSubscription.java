package com.example.prototypeai.subscription.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class UserSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private SubscriptionType subscriptionType;

    public enum SubscriptionType {
        FREE,
        PREMIUM,
        COMPANY
    }

}

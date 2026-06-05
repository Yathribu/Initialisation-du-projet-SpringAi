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

    @Getter
    public enum SubscriptionType {
        FREE(0, 5),
        PREMIUM(1,10),
        COMPANY(2, Integer.MAX_VALUE);

        SubscriptionType(int level, int numberOfRequestsPer60seconds) {
            this.level = level;
            this.numberOfRequestsPer60seconds = numberOfRequestsPer60seconds;
        }

        public final int level;

        public final int numberOfRequestsPer60seconds;

    }

}

package com.example.prototypeai.util.enums;

import com.example.prototypeai.subscription.entity.UserSubscription;
import lombok.Getter;

@Getter
public enum RequestType {
        GROK_FAST(UserSubscription.SubscriptionType.FREE),
        GROK_POWERFULL(UserSubscription.SubscriptionType.PREMIUM),
        OPEN_AI(UserSubscription.SubscriptionType.COMPANY);

        RequestType(UserSubscription.SubscriptionType minimalSubscription) {
                this.minimalSubscription = minimalSubscription;
        }

        private final UserSubscription.SubscriptionType minimalSubscription;

}

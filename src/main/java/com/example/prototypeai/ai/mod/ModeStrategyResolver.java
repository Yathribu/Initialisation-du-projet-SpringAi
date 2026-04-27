package com.example.prototypeai.ai.mod;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ModeStrategyResolver {

    private final Map<String, ModStrategy> strategies;

    public ModStrategy get(Mod mod) {
        return strategies.get(mod.name());
    }

}

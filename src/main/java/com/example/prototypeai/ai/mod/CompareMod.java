package com.example.prototypeai.ai.mod;

import com.example.prototypeai.ai.client.AskAi;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("COMPARE")
public class CompareMod implements ModStrategy {

    @Override
    public List<AskAi> applyMod(List<AskAi> pool) {
        return pool.stream().limit(2).toList();
    }
}

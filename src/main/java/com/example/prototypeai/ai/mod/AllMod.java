package com.example.prototypeai.ai.mod;

import com.example.prototypeai.ai.client.AskAi;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("ALL")
public class AllMod implements ModStrategy {

    @Override
    public List<AskAi> applyMod(List<AskAi> pool) {
        return pool;
    }
}

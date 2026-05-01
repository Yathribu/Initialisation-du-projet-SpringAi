package com.example.prototypeai.ai.mod;

import com.example.prototypeai.ai.client.AskAi;
import java.util.List;

public class SingleMod implements ModStrategy{

    @Override
    public List<AskAi> applyMod(List<AskAi> pool) {
        return pool.stream().findFirst().stream().toList();
    }

}

package com.example.prototypeai.ai.mod;

import com.example.prototypeai.ai.client.AskAi;
import java.util.List;

public interface ModStrategy {

    List<AskAi> applyMod(List<AskAi> pool);
}

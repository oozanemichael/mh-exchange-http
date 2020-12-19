package org.market.hedge.bibox.coinswap.dto.trade.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BiboxCoinSwapOrder{

    @JsonProperty("cmd")
    String cmd;

    public BiboxCoinSwapOrder(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return cmd;
    }
}

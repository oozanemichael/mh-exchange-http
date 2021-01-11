package org.market.hedge.bibox.coinswap.dto.trade.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BiboxCoinSwapReqBo {

    /**
     * true		合约符号		5BTC_USD,5ETH_USD, ...
     */
    @JsonProperty("pair")
    private String pair;

    public BiboxCoinSwapReqBo(String pair) {
        this.pair = pair;
    }

    public String getPair() {
        return pair;
    }
}

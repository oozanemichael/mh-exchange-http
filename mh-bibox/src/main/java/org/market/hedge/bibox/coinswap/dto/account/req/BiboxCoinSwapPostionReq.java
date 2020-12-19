package org.market.hedge.bibox.coinswap.dto.account.req;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BiboxCoinSwapPostionReq {

    /**
     * false		合约符号		5BTC_USD,5ETH_USD, ...
     */
    @JsonProperty("pair")
    private String pair;

    /**
     * false		仓位方向，1多仓, 2空仓		1，2
     */
    @JsonProperty("side")
    private Integer side;

    public BiboxCoinSwapPostionReq(String pair, Integer side) {
        super();
        this.pair = pair;
        this.side = side;
    }

    public String getPair() {
        return pair;
    }

    public Integer getSide() {
        return side;
    }
}

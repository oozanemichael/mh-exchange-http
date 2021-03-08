package org.market.hedge.okex.dto.marketdata.resp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class OkexDepthResp {

    /**
     *  asks	Array	卖方深度
     */
    List<BigDecimal> asks;

    /**
     *  bids	Array	买方深度
     */
    List<BigDecimal> bids;

    /**
     * ts	String	深度产生的时间
     */
    List<BigDecimal> ts;

    @JsonCreator
    public OkexDepthResp(
            @JsonProperty("status") List<BigDecimal> asks,
            @JsonProperty("ts") List<BigDecimal> bids,
            @JsonProperty("tick") List<BigDecimal> ts) {
        this.asks=asks;
        this.bids=bids;
        this.ts=ts;
    }

    public List<BigDecimal> getAsks() {
        return asks;
    }

    public List<BigDecimal> getBids() {
        return bids;
    }

    public List<BigDecimal> getTs() {
        return ts;
    }
}

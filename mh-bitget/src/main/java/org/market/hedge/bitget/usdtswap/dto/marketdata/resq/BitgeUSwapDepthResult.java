package org.market.hedge.bitget.usdtswap.dto.marketdata.resq;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author chuxianbo
 */
public class BitgeUSwapDepthResult {

    List<List<String>> asks;

    List<List<String>> bids;

    String timestamp;


    public BitgeUSwapDepthResult(
            @JsonProperty("asks") List<List<String>> asks,
            @JsonProperty("bids")  List<List<String>> bids,
            @JsonProperty("timestamp")  String timestamp) {
        this.asks = asks;
        this.bids = bids;
        this.timestamp = timestamp;
    }

    public List<List<String>> getAsks() {
        return asks;
    }

    public List<List<String>> getBids() {
        return bids;
    }

    public String getTimestamp() {
        return timestamp;
    }

}

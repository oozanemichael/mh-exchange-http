package org.market.hedge.huobi.swap.dto.trader;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HuobiSwapCreateBatchOrderRequest {

    @JsonProperty("orders_data")
    public List<HuobiSwapCreateOrderRequest> ordersData;

    public HuobiSwapCreateBatchOrderRequest(
            List<HuobiSwapCreateOrderRequest> ordersData
    ) {
        this.ordersData = ordersData;
    }

    public List<HuobiSwapCreateOrderRequest> getOrdersData() {
        return ordersData;
    }

}

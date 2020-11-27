package org.market.hedge.deribit.option.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class HuobiOptionCreateOrdersRequest {

    @JsonProperty("orders_data")
    public List<HuobiOptionCreateOrderRequest> ordersData;

    public HuobiOptionCreateOrdersRequest(
            List<HuobiOptionCreateOrderRequest> ordersData
    ) {
        this.ordersData = ordersData;
    }

    public List<HuobiOptionCreateOrderRequest> getOrdersData() {
        return ordersData;
    }

}

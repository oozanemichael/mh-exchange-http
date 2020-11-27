package org.market.hedge.deribit.option.dto.trader.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.deribit.option.dto.HuobiOptionResult;

public class HuobiOptionPlaceOrdersResult extends HuobiOptionResult<HuobiOptionPlaceOrdersDataResult> {

    public HuobiOptionPlaceOrdersResult(@JsonProperty("status")String status,
                                        @JsonProperty("ts")Long ts,
                                        @JsonProperty("errCode")String errCode,
                                        @JsonProperty("errMsg")String errMsg,
                                        @JsonProperty("data")HuobiOptionPlaceOrdersDataResult data) {
        super(status, ts, errCode, errMsg, data);
    }
}

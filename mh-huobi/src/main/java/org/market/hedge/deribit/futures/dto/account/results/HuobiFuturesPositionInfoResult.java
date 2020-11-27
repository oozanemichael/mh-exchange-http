package org.market.hedge.deribit.futures.dto.account.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.deribit.futures.dto.account.HuobiFuturesPosition;
import org.market.hedge.deribit.option.dto.HuobiOptionResult;

import java.util.List;

public class HuobiFuturesPositionInfoResult  extends HuobiOptionResult<List<HuobiFuturesPosition>> {

    public HuobiFuturesPositionInfoResult(
            @JsonProperty("status") String status,
            @JsonProperty("ts")  Long ts,
            @JsonProperty("errCode") String errCode,
            @JsonProperty("errMsg") String errMsg,
            @JsonProperty("data") List<HuobiFuturesPosition> data) {
        super(status, ts, errCode, errMsg, data);
    }
}

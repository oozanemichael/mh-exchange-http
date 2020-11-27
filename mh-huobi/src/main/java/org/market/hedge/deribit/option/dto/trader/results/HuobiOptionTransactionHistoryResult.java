package org.market.hedge.deribit.option.dto.trader.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.deribit.dto.HuobiResult;
import org.market.hedge.deribit.option.dto.trader.HuobiOptionTransactionHistoryData;

import java.util.Date;

public class HuobiOptionTransactionHistoryResult extends HuobiResult<HuobiOptionTransactionHistoryData> {

    private final Date ts;

    public HuobiOptionTransactionHistoryResult(
            @JsonProperty("status") String status,
            @JsonProperty("ts") Date ts,
            @JsonProperty("errCode") String errCode,
            @JsonProperty("errMsg") String errMsg,
            @JsonProperty("data") HuobiOptionTransactionHistoryData data) {
        super(status, errCode, errMsg, data);
        this.ts=ts;
    }

    public Date getTs() {
        return ts;
    }
}


package org.market.hedge.deribit.option.dto.marketdata.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.deribit.dto.HuobiResult;
import org.market.hedge.deribit.option.dto.marketdata.HuobiOptionContractInfo;

import java.util.Date;

public class HuobiOptionContractInfoResult extends HuobiResult<HuobiOptionContractInfo[]> {

    private final Date ts;

    public HuobiOptionContractInfoResult(
            @JsonProperty("status") String status,
            @JsonProperty("ts") Date ts,
            @JsonProperty("errCode") String errCode,
            @JsonProperty("errMsg") String errMsg,
            @JsonProperty("data") HuobiOptionContractInfo[] data) {
        super(status, errCode, errMsg, data);
        this.ts=ts;
    }

    public Date getTs() {
        return ts;
    }
}

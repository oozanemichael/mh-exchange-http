package org.market.hedge.deribit.dto.account.results;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.deribit.dto.HuobiResult;
import org.market.hedge.deribit.dto.account.HuobiFeeRate;

public class HuobiFeeRateResult extends HuobiResult<HuobiFeeRate[]> {
  @JsonCreator
  public HuobiFeeRateResult(
      @JsonProperty("status") String status,
      @JsonProperty("data") HuobiFeeRate[] data,
      @JsonProperty("err-code") String errCode,
      @JsonProperty("err-msg") String errMsg) {
    super(status, errCode, errMsg, data);
  }
}

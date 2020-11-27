package org.market.hedge.deribit.dto.trade.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.deribit.dto.HuobiResult;

public class HuobiOrderResult extends HuobiResult<String> {

  public HuobiOrderResult(
      @JsonProperty("status") String status,
      @JsonProperty("data") String result,
      @JsonProperty("err-code") String errCode,
      @JsonProperty("err-msg") String errMsg) {
    super(status, errCode, errMsg, result);
  }
}

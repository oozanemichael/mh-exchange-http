package org.market.hedge.deribit.dto.account.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.deribit.dto.HuobiResult;
import org.market.hedge.deribit.dto.account.HuobiWithdrawFeeRange;

public class HuobiWithdrawFeeRangeResult extends HuobiResult<HuobiWithdrawFeeRange> {

  public HuobiWithdrawFeeRangeResult(
      @JsonProperty("status") String status,
      @JsonProperty("data") HuobiWithdrawFeeRange result,
      @JsonProperty("err-code") String errCode,
      @JsonProperty("err-msg") String errMsg) {
    super(status, errCode, errMsg, result);
  }
}

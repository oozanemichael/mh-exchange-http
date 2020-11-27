package org.market.hedge.deribit.dto.account.results;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.market.hedge.deribit.dto.HuobiResultV2;
import org.market.hedge.deribit.dto.account.HuobiTransactFeeRate;

public class HuobiTransactFeeRateResult extends HuobiResultV2<HuobiTransactFeeRate[]> {
  @JsonCreator
  public HuobiTransactFeeRateResult(
      @JsonProperty("code") String code,
      @JsonProperty("message") String message,
      @JsonProperty("data") HuobiTransactFeeRate[] data) {
    super(code, message, data);
  }
}

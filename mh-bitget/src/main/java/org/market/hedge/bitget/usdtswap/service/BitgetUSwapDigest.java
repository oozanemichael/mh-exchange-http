package org.market.hedge.bitget.usdtswap.service;

import org.knowm.xchange.service.BaseParamsDigest;
import si.mazi.rescu.RestInvocation;

public class BitgetUSwapDigest extends BaseParamsDigest {


  protected BitgetUSwapDigest(String secretKeyBase64, String hmacString) throws IllegalArgumentException {
    super(secretKeyBase64, hmacString);
  }

  @Override
  public String digestParams(RestInvocation restInvocation) {
    return null;
  }

}

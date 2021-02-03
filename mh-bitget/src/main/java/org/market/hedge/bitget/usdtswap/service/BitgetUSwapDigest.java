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

  /**
   * queryString为空时，签名格式
   *
   * timestamp + method.toUpperCase() + requestPath + body
   *
   * queryString不为空时，签名格式
   *
   * timestamp + method.toUpperCase() + requestPath + "?" + queryString + body
   * */
  public static String buildSignature(long timestamp,String method,String requestPath,String queryString,String body) {
    String sign=null;
    if (queryString.isEmpty()){
      sign=timestamp + method.toUpperCase() + requestPath + body;
    }else {
      sign=timestamp + method.toUpperCase() + requestPath + "?" + queryString + body;
    }

    return null;
  }



}

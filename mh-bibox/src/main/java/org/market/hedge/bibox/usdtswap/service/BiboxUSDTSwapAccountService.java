package org.market.hedge.bibox.usdtswap.service;

import org.knowm.xchange.Exchange;
import org.market.hedge.service.account.MHAccountService;

/** @author odrotleff */
public class BiboxUSDTSwapAccountService extends BiboxUSDTSwapAccountServiceRaw implements MHAccountService {

  public BiboxUSDTSwapAccountService(Exchange exchange) {
    super(exchange);
  }
}

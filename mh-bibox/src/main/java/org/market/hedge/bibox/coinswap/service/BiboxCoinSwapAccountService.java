package org.market.hedge.bibox.coinswap.service;

import org.knowm.xchange.Exchange;
import org.market.hedge.service.account.MHAccountService;

/** @author odrotleff */
public class BiboxCoinSwapAccountService extends BiboxCoinSwapAccountServiceRaw implements MHAccountService {

  public BiboxCoinSwapAccountService(Exchange exchange) {
    super(exchange);
  }
}

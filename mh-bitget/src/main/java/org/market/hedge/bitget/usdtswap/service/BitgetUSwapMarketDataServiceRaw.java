package org.market.hedge.bitget.usdtswap.service;

import org.knowm.xchange.Exchange;

/** @author odrotleff */
public class BitgetUSwapMarketDataServiceRaw extends BitgetUSwapBaseService {

  private static final String TICKER_CMD = "ticker";
  private static final String DEPTH_CMD = "depth";
  private static final String ALL_TICKERS_CMD = "marketAll";
  private static final String DEALS_CMD = "deals";

  protected BitgetUSwapMarketDataServiceRaw(Exchange exchange) {
    super(exchange);
  }

}

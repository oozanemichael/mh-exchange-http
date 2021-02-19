package org.market.hedge.deribit.usdtswap.service;

import org.knowm.xchange.Exchange;
import org.market.hedge.deribit.usdtswap.dto.marketdata.resq.BitgeUSwapDepthResult;
import org.market.hedge.core.ParsingCurrencyPair;

import java.io.IOException;

/** @author odrotleff */
public abstract class BitgetUSwapMarketDataServiceRaw extends BitgetUSwapBaseService {

  private static final String TICKER_CMD = "ticker";
  private static final String DEPTH_CMD = "depth";
  private static final String ALL_TICKERS_CMD = "marketAll";
  private static final String DEALS_CMD = "deals";

  /**
   * Constructor
   *
   */
  protected BitgetUSwapMarketDataServiceRaw( Exchange exchange) {
    super(exchange);
  }


  public BitgeUSwapDepthResult getBitgetDepth(ParsingCurrencyPair parsingCurrencyPair, Integer depthType) throws IOException {
    BitgeUSwapDepthResult depthResult = bitget.getDepth(parsingCurrencyPair.getParsing(), depthType);
    return depthResult;
  }

}

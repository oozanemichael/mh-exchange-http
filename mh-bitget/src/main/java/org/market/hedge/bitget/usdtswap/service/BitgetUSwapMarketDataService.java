package org.market.hedge.bitget.usdtswap.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.market.hedge.bitget.usdtswap.dto.marketdata.resq.BitgeUSwapDepthResult;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.service.marketdata.MHMarketDataService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the market data service for Bibox
 *
 * <ul>
 *   <li>Provides access to various market data values
 * </ul>
 *
 * @author odrotleff
 */
public class BitgetUSwapMarketDataService extends BitgetUSwapMarketDataServiceRaw implements MHMarketDataService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public BitgetUSwapMarketDataService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public OrderBook getOrderBook(ParsingCurrencyPair currencyPair, Object... args) throws IOException {
    BitgeUSwapDepthResult result=bibox.getDepth(currencyPair.getParsing(),5);


    return null;
  }
}

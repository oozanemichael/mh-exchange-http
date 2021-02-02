package org.market.hedge.bitget.usdtswap.service;

import org.knowm.xchange.Exchange;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.service.trade.MHTradeService;

import java.io.IOException;
import java.util.List;

/** @author odrotleff */
public class BitgetUSwapTradeService extends BitgetUSwapTradeServiceRaw implements MHTradeService {

  public BitgetUSwapTradeService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public List<String> placeLimitOrders(List<MHLimitOrder> limitOrders) throws IOException {
    return null;
  }

  @Override
  public void cancelAllByInstrument(ParsingCurrencyPair parsingCurrencyPair) throws IOException {

  }
}

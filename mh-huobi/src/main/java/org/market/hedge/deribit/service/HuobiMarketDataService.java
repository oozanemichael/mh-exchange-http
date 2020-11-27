package org.market.hedge.deribit.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.marketdata.Trade;
import org.market.hedge.core.ParsingCurrencyPair;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.exceptions.ExchangeException;
import org.market.hedge.deribit.HuobiAdapters;
import org.market.hedge.deribit.dto.marketdata.HuobiDepth;
import org.market.hedge.deribit.dto.marketdata.HuobiTradeWrapper;
import org.market.hedge.service.marketdata.MHMarketDataService;
import org.knowm.xchange.service.marketdata.params.Params;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HuobiMarketDataService extends HuobiMarketDataServiceRaw implements MHMarketDataService {

  public HuobiMarketDataService(Exchange exchange) {
    super(exchange);
  }

  @Override
  public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {
    return HuobiAdapters.adaptTicker(getHuobiTicker(currencyPair), currencyPair);
  }

  @Override
  public List<Ticker> getTickers(Params params) throws IOException {
    return HuobiAdapters.adaptAllTickers(getHuobiAllTickers());
  }

  @Override
  public OrderBook getOrderBook(ParsingCurrencyPair currencyPair, Object... args) throws IOException {

    String depthType = "step0";

    if (args != null && args.length == 1) {
      Object arg0 = args[0];
      if (!(arg0 instanceof String)) {
        throw new ExchangeException("Argument 0 must be an String!");
      } else {
        depthType = (String) arg0;
      }
    }
    HuobiDepth depth = getHuobiDepth(currencyPair.getCurrencyPair(), depthType);
    List<LimitOrder> bids =
        depth.getBids().entrySet().stream()
            .map(
                e ->
                    new LimitOrder(
                        OrderType.BID, e.getValue(), currencyPair.getCurrencyPair(), null, null, e.getKey()))
            .collect(Collectors.toList());
    List<LimitOrder> asks =
        depth.getAsks().entrySet().stream()
            .map(
                e ->
                    new LimitOrder(
                        OrderType.ASK, e.getValue(), currencyPair.getCurrencyPair(), null, null, e.getKey()))
            .collect(Collectors.toList());
    return new OrderBook(depth.getTs(), asks, bids);
  }

  @Override
  public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {
    int size = 100;

    if (args != null && args.length == 1) {
      Object arg0 = args[0];
      if (!(arg0 instanceof Integer) || arg0 == null || (int) arg0 < 1 || (int) arg0 > 2000) {
        throw new ExchangeException("Argument 0 must be an Integer in the range [1, 2000]!");
      } else {
        size = (int) arg0;
      }
    }

    HuobiTradeWrapper[] huobiTrades = getHuobiTrades(currencyPair, size);
    List<Trade> trades =
        Arrays.asList(huobiTrades).stream()
            .map(t -> t.getData()[0])
            .map(
                t ->
                    new Trade.Builder()
                        .type(HuobiAdapters.adaptOrderType(t.getDirection()))
                        .originalAmount(t.getAmount())
                        .currencyPair(currencyPair)
                        .price(t.getPrice())
                        .timestamp(t.getTs())
                        .id(t.getId())
                        .build())
            .collect(Collectors.toList());

    return new Trades(trades);
  }



}

package org.market.hedge.okex.service;


import com.sun.org.apache.xalan.internal.utils.XMLSecurityManager;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.okex.dto.marketdata.resp.OkexDepthResp;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OkexMarketDataServiceRaw extends OkexBaseService {

    protected OkexMarketDataServiceRaw(Exchange exchange) {
        super(exchange);
    }

    public OrderBook books(ParsingCurrencyPair pair , Integer limit) throws IOException{

        OkexDepthResp result = okex.books(pair.getParsing(),limit.toString());
        List<LimitOrder> bids =
                result.getData().get(0).getBids().stream()
                        .map(
                                e ->
                                        new LimitOrder(
                                                Order.OrderType.BID, e.get(1), pair.getCurrencyPair(), null, null, e.get(0)))
                        .collect(Collectors.toList());
        List<LimitOrder> asks =
                result.getData().get(0).getAsks().stream()
                        .map(
                                e ->
                                        new LimitOrder(
                                                Order.OrderType.ASK, e.get(1), pair.getCurrencyPair(), null, null, e.get(0)))
                        .collect(Collectors.toList());
        return new OrderBook(new Date(Long.parseLong(result.getData().get(0).getTs())), asks, bids);

    }

}

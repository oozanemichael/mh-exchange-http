package org.market.hedge.binance.option.service;

import com.alibaba.fastjson.JSON;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.market.hedge.binance.*;
import org.market.hedge.binance.dto.BinanceException;
import org.market.hedge.binance.dto.trade.BinanceCancelledOrder;
import org.market.hedge.binance.dto.trade.OrderSide;
import org.market.hedge.binance.dto.trade.OrderType;
import org.market.hedge.binance.dto.trade.TimeInForce;
import org.market.hedge.binance.option.BinanceOptionAuthenticated;
import org.market.hedge.binance.option.dto.trade.req.BinanceOptionOrder;
import org.market.hedge.binance.perpetualSwap.BinancePerpetualAuthenticated;
import org.market.hedge.binance.perpetualSwap.dto.trade.req.BinancePerpetualOrder;
import org.market.hedge.binance.service.BinanceTradeService;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.dto.trade.MHMarketOrder;

import javax.ws.rs.FormParam;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.market.hedge.binance.BinanceResilience.REQUEST_WEIGHT_RATE_LIMITER;

public class BinanceOptionTradeServiceRaw extends BinanceOptionBaseService {

    protected BinanceOptionTradeServiceRaw(BinanceExchange exchange, BinanceOptionAuthenticated binance, ResilienceRegistries resilienceRegistries) {
        super(exchange, binance, resilienceRegistries);
    }


    protected String placeOrdersLimit(List<MHLimitOrder> limitOrders) throws IOException {
        OrderType type=OrderType.LIMIT;
        List<BinanceOptionOrder> batchOrders=new ArrayList<BinanceOptionOrder>();
        limitOrders.forEach(e->{
            BinanceOptionOrder newOrder =
                    BinanceOptionOrder.builder()
                            .side(BinanceAdapters.convert(e.getType()))
                            .symbol(e.getParsingCurrencyPair().getParsing())
                            .type(type)
                            .clientOrderId(getClientOrderId(e))
                            .quantity(e.getOriginalAmount())
                            .price(e.getLimitPrice())
                            .build();
            batchOrders.add(newOrder);
        });
        BinanceOptionOrder[] strings = new BinanceOptionOrder[batchOrders.size()];
        batchOrders.toArray(strings);
        try {
            UrlParamsBuilder builder =UrlParamsBuilder.build()
                    .putToUrl("orders",JSON.toJSONString(strings))
                    .setMethod("POST");

            String signature=BinanceSignature.createSignature(apiKey,secretKey,builder);

            binance.batchOrders(JSON.toJSONString(strings),BinanceOptionApiConstants.DEFAULT_RECEIVING_WINDOW,getTimestampFactory(),apiKey,signature);
            return "success";
        } catch (BinanceException e) {
            throw BinanceErrorAdapter.adapt(e);
        }
    }

    protected String placeOrdersMarket(List<MHMarketOrder> marketOrder)
            throws IOException {
        OrderType type=OrderType.MARKET;
        List<BinancePerpetualOrder> batchOrders=new ArrayList<BinancePerpetualOrder>();
        marketOrder.forEach(e->{
            BinancePerpetualOrder newOrder =
                    newOrder(
                            e.getParsingCurrencyPair(),
                            BinanceAdapters.convert(e.getType()),
                            type,
                            null,
                            e.getOriginalAmount(),
                            null,
                            getClientOrderId(e),
                            null);
        });
        BinancePerpetualOrder[] strings = new BinancePerpetualOrder[batchOrders.size()];
        batchOrders.toArray(strings);

        try {
            binance.batchOrders(JSON.toJSONString(strings),null,getTimestampFactory(),apiKey,"signatureCreator");
            return "success";
        } catch (BinanceException e) {
            throw BinanceErrorAdapter.adapt(e);
        }
    }

    private String getClientOrderId(Order order) {

        String clientOrderId = null;
        for (Order.IOrderFlags flags : order.getOrderFlags()) {
            if (flags instanceof BinanceTradeService.BinanceOrderFlags) {
                BinanceTradeService.BinanceOrderFlags bof = (BinanceTradeService.BinanceOrderFlags) flags;
                if (clientOrderId == null) {
                    clientOrderId = bof.getClientId();
                }
            }
        }
        return clientOrderId;
    }

    public BinancePerpetualOrder newOrder(
            ParsingCurrencyPair pair,
            OrderSide side,
            OrderType type,
            TimeInForce timeInForce,
            BigDecimal quantity,
            BigDecimal price,
            String newClientOrderId,
            BigDecimal stopPrice) {

        return new BinancePerpetualOrder(
                pair.getParsing(),
                side,
                type,
                timeInForce,
                quantity,
                price,
                newClientOrderId,
                stopPrice);

    }

    public List<BinanceCancelledOrder> cancelAllOpenOrders(String symbol)
            throws IOException, BinanceException {
        return decorateApiCall(
                () ->
                        binance.cancelAllOpenOrders(
                                symbol,
                                getRecvWindow(),
                                getTimestampFactory(),
                                super.apiKey,
                                super.signatureCreator))
                .withRetry(retry("cancelAllOpenOrders"))
                .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
                .call();
    }

}

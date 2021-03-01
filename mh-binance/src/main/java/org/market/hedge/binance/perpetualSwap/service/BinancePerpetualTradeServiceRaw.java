package org.market.hedge.binance.perpetualSwap.service;

import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.market.hedge.binance.BinanceAdapters;
import org.market.hedge.binance.BinanceErrorAdapter;
import org.market.hedge.binance.BinanceExchange;
import org.market.hedge.binance.dto.BinanceException;
import org.market.hedge.binance.dto.trade.BinanceNewOrder;
import org.market.hedge.binance.dto.trade.OrderSide;
import org.market.hedge.binance.dto.trade.OrderType;
import org.market.hedge.binance.dto.trade.TimeInForce;
import org.market.hedge.binance.perpetualSwap.BinancePerpetualAuthenticated;
import org.market.hedge.binance.perpetualSwap.dto.trade.req.BinancePerpetualOrder;
import org.market.hedge.binance.service.BinanceTradeService;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.dto.trade.MHMarketOrder;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.knowm.xchange.client.ResilienceRegistries.NON_IDEMPOTENTE_CALLS_RETRY_CONFIG_NAME;
import static org.market.hedge.binance.BinanceResilience.*;

public class BinancePerpetualTradeServiceRaw extends BinancePerpetualBaseService{

    protected BinancePerpetualTradeServiceRaw(BinanceExchange exchange, BinancePerpetualAuthenticated binance, ResilienceRegistries resilienceRegistries) {
        super(exchange, binance, resilienceRegistries);
    }


    protected String placeOrders(List<MHMarketOrder> marketOrder)
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
        try {
            binance.batchOrders(batchOrders,null,getTimestampFactory(),apiKey,signatureCreator);
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

}

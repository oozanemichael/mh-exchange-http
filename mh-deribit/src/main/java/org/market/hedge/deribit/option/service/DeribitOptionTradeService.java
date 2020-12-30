package org.market.hedge.deribit.option.service;

import org.knowm.xchange.dto.Order;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.deribit.DeribitExchange;
import org.market.hedge.deribit.dto.trade.OrderPlacement;
import org.market.hedge.deribit.service.DeribitTradeServiceRaw;
import org.market.hedge.dto.trade.MHLimitOrder;
import org.market.hedge.service.trade.MHTradeService;

import java.io.IOException;

public class DeribitOptionTradeService extends DeribitTradeServiceRaw implements MHTradeService {

    public DeribitOptionTradeService(DeribitExchange exchange) {
        super(exchange);
    }


    @Override
    public String placeLimitOrder(MHLimitOrder limitOrder) throws IOException {
        OrderPlacement placement=null;
        if (limitOrder.getType()== Order.OrderType.BID || limitOrder.getType()== Order.OrderType.EXIT_BID){
            placement=buy(limitOrder.getParsingCurrencyPair().getParsing(),
                    limitOrder.getOriginalAmount(),
                    null,
                    null,
                    limitOrder.getLimitPrice(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);
        }else{
            placement=sell(limitOrder.getParsingCurrencyPair().getParsing(),
                    limitOrder.getOriginalAmount(),
                    null,
                    null,
                    limitOrder.getLimitPrice(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);
        }
        return placement.getOrder().getOrderId();
    }

    @Override
    public void cancelAllByInstrument(ParsingCurrencyPair parsingCurrencyPair) throws IOException {
        deribitAuthenticated.cancelAllByInstrument("parsingCurrencyPair.getParsing()", null, deribitAuth);
    }

}

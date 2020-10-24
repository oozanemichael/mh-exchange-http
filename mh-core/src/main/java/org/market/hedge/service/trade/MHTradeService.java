package org.market.hedge.service.trade;

import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.StopOrder;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.CancelOrderParams;
import org.knowm.xchange.service.trade.params.DefaultCancelOrderParamId;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.orders.DefaultQueryOrderParam;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParams;
import org.knowm.xchange.service.trade.params.orders.OrderQueryParams;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.dto.trade.MHLimitOrder;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface MHTradeService extends TradeService {


    default List<String> placeLimitOrders(List<MHLimitOrder> LimitOrders) throws IOException {
        throw new NotYetImplementedForExchangeException();
    }


    default String placeLimitOrder(MHLimitOrder limitOrder) throws IOException {
        throw new NotYetImplementedForExchangeException();
    }

    default String changeOrder(MHLimitOrder limitOrder) throws IOException {
        this.cancelOrder(limitOrder.getId());
        return this.placeLimitOrder(limitOrder);
    }


    default void verifyOrder(MHLimitOrder limitOrder) {
        throw new NotYetImplementedForExchangeException();
    }


    default void cancelAll(ParsingCurrencyPair parsingCurrencyPair) throws IOException {
        throw new NotYetImplementedForExchangeException();
    }
}

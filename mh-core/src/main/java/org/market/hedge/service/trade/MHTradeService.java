package org.market.hedge.service.trade;

import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.trade.TradeService;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.dto.trade.MHLimitOrder;

import java.io.IOException;
import java.util.List;

/**
 * 交易
 * Auth
 * */
public interface MHTradeService extends TradeService {

    /**
    * 下多个订单
    * */
    default List<String> placeLimitOrders(List<MHLimitOrder> LimitOrders) throws IOException {
        throw new NotYetImplementedForExchangeException();
    }

    /**
     * 下单个
     * */
    default String placeLimitOrder(MHLimitOrder limitOrder) throws IOException {
        throw new NotYetImplementedForExchangeException();
    }

    /**
     * 撤销单个订单
     * */
    default String changeOrder(MHLimitOrder limitOrder) throws IOException {
        this.cancelOrder(limitOrder.getId());
        return this.placeLimitOrder(limitOrder);
    }


    default void verifyOrder(MHLimitOrder limitOrder) {
        throw new NotYetImplementedForExchangeException();
    }

    /**
     * 根据合约名称撤销关于此合约的所有订单
     * */
    default void cancelAll(ParsingCurrencyPair parsingCurrencyPair) throws IOException {
        throw new NotYetImplementedForExchangeException();
    }
}

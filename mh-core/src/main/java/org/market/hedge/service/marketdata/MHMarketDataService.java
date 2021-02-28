package org.market.hedge.service.marketdata;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.utils.StreamUtils;
import org.market.hedge.core.Kline;
import org.market.hedge.core.KlineInterval;
import org.market.hedge.core.ParsingCurrencyPair;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 市场
 * */
public interface MHMarketDataService extends MarketDataService {

    /**
     * 获取盘口行情
     * */
    default OrderBook getOrderBook(ParsingCurrencyPair currencyPair, Object... args) throws IOException {
        throw new NotYetImplementedForExchangeException();
    }

    default Kline getLastKline(ParsingCurrencyPair pair, KlineInterval interval) throws IOException {
        throw new NotYetImplementedForExchangeException();    }

    default List<Kline> getKlines(ParsingCurrencyPair pair, KlineInterval interval, Integer limit) throws IOException {
        throw new NotYetImplementedForExchangeException();    }





}

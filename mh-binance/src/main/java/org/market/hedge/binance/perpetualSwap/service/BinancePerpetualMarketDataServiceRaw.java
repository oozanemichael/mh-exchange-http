package org.market.hedge.binance.perpetualSwap.service;

import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.binance.BinanceAdapters;
import org.market.hedge.binance.BinanceExchange;
import org.market.hedge.binance.dto.marketdata.BinanceKline;
import org.market.hedge.core.Kline;
import org.market.hedge.core.KlineInterval;
import org.market.hedge.binance.perpetualSwap.BinancePerpetualAuthenticated;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static org.market.hedge.binance.BinanceResilience.REQUEST_WEIGHT_RATE_LIMITER;

public class BinancePerpetualMarketDataServiceRaw extends BinancePerpetualBaseService{
    protected BinancePerpetualMarketDataServiceRaw(BinanceExchange exchange, BinancePerpetualAuthenticated binance, ResilienceRegistries resilienceRegistries) {
        super(exchange, binance, resilienceRegistries);
    }

    public List<Kline> klines(
            CurrencyPair pair, KlineInterval interval, Integer limit, Long startTime, Long endTime)
            throws IOException {
        List<Object[]> raw =
                decorateApiCall(
                        () ->
                                binance.klines(
                                        BinanceAdapters.toSymbol(pair), interval.code(), limit, startTime, endTime))
                        .withRetry(retry("klines"))
                        .withRateLimiter(rateLimiter(REQUEST_WEIGHT_RATE_LIMITER))
                        .call();
        return raw.stream()
                .map(obj -> newKline(pair, interval, obj))
                .collect(Collectors.toList());
    }

    public Kline newKline(CurrencyPair pair, KlineInterval interval, Object[] obj) {
        return new Kline( pair,
                interval,
                Long.valueOf(obj[0].toString()),
                new BigDecimal(obj[1].toString()),
                new BigDecimal(obj[2].toString()),
                new BigDecimal(obj[3].toString()),
                new BigDecimal(obj[4].toString()),
                new BigDecimal(obj[5].toString()),
                Long.valueOf(obj[6].toString()),
                new BigDecimal(obj[7].toString()),
                Long.valueOf(obj[8].toString()),
                new BigDecimal(obj[9].toString()),
                new BigDecimal(obj[10].toString()));
    }



}

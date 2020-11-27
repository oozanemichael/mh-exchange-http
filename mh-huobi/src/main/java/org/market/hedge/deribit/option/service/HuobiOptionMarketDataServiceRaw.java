package org.market.hedge.deribit.option.service;


import org.knowm.xchange.Exchange;
import org.market.hedge.core.ParsingCurrencyPair;
import org.market.hedge.deribit.dto.marketdata.HuobiDepth;
import org.market.hedge.deribit.dto.marketdata.results.HuobiDepthResult;

import java.io.IOException;

public class HuobiOptionMarketDataServiceRaw extends HuobiOptionBaseService {


    protected HuobiOptionMarketDataServiceRaw(Exchange exchange) {
        super(exchange);
    }

    public HuobiDepth getHuobiDepth(ParsingCurrencyPair parsingCurrencyPair, String depthType) throws IOException {
        HuobiDepthResult depthResult = huobiOption.getDepth(parsingCurrencyPair.getParsing(), depthType);
        return checkResult(depthResult);
    }

}

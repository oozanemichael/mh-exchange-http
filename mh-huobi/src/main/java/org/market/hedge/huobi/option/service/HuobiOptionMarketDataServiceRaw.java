package org.market.hedge.huobi.option.service;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.Exchange;
import org.market.hedge.core.ParsingCurrencyPair;
import org.knowm.xchange.currency.CurrencyPair;
import org.market.hedge.huobi.Huobi;
import org.market.hedge.huobi.HuobiUtils;
import org.market.hedge.huobi.dto.marketdata.HuobiDepth;
import org.market.hedge.huobi.dto.marketdata.results.HuobiDepthResult;
import org.market.hedge.huobi.option.HuobiOption;
import org.market.hedge.huobi.option.dto.marketdata.HuobiOptionContractInfo;
import org.market.hedge.huobi.option.dto.marketdata.results.HuobiOptionContractInfoResult;

import java.io.IOException;
import java.util.List;

public class HuobiOptionMarketDataServiceRaw extends HuobiOptionBaseService {


    protected HuobiOptionMarketDataServiceRaw(Exchange exchange) {
        super(exchange);
    }

    public HuobiDepth getHuobiDepth(ParsingCurrencyPair parsingCurrencyPair, String depthType) throws IOException {
        HuobiDepthResult depthResult = huobiOption.getDepth(parsingCurrencyPair.getParsing(), depthType);
        return checkResult(depthResult);
    }

}

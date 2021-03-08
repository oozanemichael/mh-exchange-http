package org.market.hedge.okex.service;

import org.knowm.xchange.Exchange;
import org.market.hedge.service.marketdata.MHMarketDataService;


public class OkexMarketDataService  extends OkexMarketDataServiceRaw implements MHMarketDataService {

    public OkexMarketDataService(Exchange exchange) {
        super(exchange);
    }

}

package org.market.hedge.okex.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import org.market.hedge.okex.Okex;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;


/**
 * @author chuxianbo
 */
public class OkexBaseService extends BaseExchangeService implements BaseService {

    protected Okex okex;
    protected ParamsDigest signatureCreator;

    protected OkexBaseService(Exchange exchange) {
        super(exchange);
        //okex = RestProxyFactory.createProxy(
                        //okex.class, exchange.getExchangeSpecification().getSslUri());
        //signatureCreator =
               // HuobiOptionDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
    }




}

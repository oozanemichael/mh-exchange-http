package org.market.hedge.okex.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.BaseService;
import org.market.hedge.okex.Okex;
import org.market.hedge.okex.dto.OkexResult;
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
        okex = RestProxyFactory.createProxy(
                Okex.class, exchange.getExchangeSpecification().getSslUri());
    }

    protected <R> R checkResult(OkexResult<R> okexResult) {
        if (!okexResult.isSuccess()) {
            String huobiError = okexResult.getError();
            if (huobiError.length() == 0) {
                throw new ExchangeException("Missing error message");
            } else {
                throw new ExchangeException(huobiError);
            }
        }
        return okexResult.getData();
    }




}

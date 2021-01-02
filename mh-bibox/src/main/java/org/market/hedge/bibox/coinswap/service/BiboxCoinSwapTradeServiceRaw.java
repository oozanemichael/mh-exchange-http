package org.market.hedge.bibox.coinswap.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.market.hedge.bibox.BiboxException;
import org.market.hedge.bibox.coinswap.dto.BiboxCoinSwapSingleResponse;
import org.market.hedge.bibox.coinswap.dto.trade.req.BiboxCoinSwapOrderReqBo;
import org.market.hedge.bibox.dto.BiboxCommands;
import org.market.hedge.bibox.dto.trade.BiboxOrderSide;
import org.market.hedge.bibox.dto.trade.BiboxOrderType;
import org.market.hedge.bibox.service.BiboxDigest;
import org.market.hedge.dto.trade.MHLimitOrder;


public class BiboxCoinSwapTradeServiceRaw  extends BiboxCoinSwapBaseService{
    /**
     * Constructor
     *
     * @param exchange
     */
    protected BiboxCoinSwapTradeServiceRaw(Exchange exchange) {
        super(exchange);
    }

    public String placeBiboxLimitOrder(MHLimitOrder limitOrder) {
        try {
            BiboxCoinSwapOrderReqBo cmd =new BiboxCoinSwapOrderReqBo(
                            limitOrder.getParsingCurrencyPair().getParsing(),
                            limitOrder.getOriginalAmount().toPlainString(),
                            BiboxOrderSide.fromOrderType(limitOrder.getType()).asInt(),
                            BiboxOrderType.LIMIT_ORDER.asInt(),
                            limitOrder.getLimitPrice().toPlainString(),
                            6,
                            limitOrder.getId()
                    );
            String cmdJson=BiboxCommands.toJson(cmd);
            long millis=System.currentTimeMillis();
            String sign=BiboxDigest.buildSignature(millis+cmdJson,exchange.getExchangeSpecification().getSecretKey());
            BiboxCoinSwapSingleResponse<String> response = bibox.trade(cmdJson,apiKey,sign,String.valueOf(millis));
            throwErrors(response);
            return response.getOrder_id();
        } catch (BiboxException e) {
            throw new ExchangeException(e.getMessage());
        }
    }

}

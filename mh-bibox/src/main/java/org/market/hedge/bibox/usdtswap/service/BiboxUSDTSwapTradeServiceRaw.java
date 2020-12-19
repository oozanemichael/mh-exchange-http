package org.market.hedge.bibox.usdtswap.service;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.market.hedge.bibox.BiboxException;
import org.market.hedge.bibox.coinswap.dto.BiboxCoinSwapSingleResponse;
import org.market.hedge.bibox.dto.BiboxCommands;
import org.market.hedge.bibox.dto.trade.BiboxOrderSide;
import org.market.hedge.bibox.dto.trade.BiboxOrderType;
import org.market.hedge.bibox.service.BiboxDigest;
import org.market.hedge.bibox.usdtswap.dto.trade.req.BiboxUSDTSwapPostionReq;
import org.market.hedge.dto.trade.MHLimitOrder;

public class BiboxUSDTSwapTradeServiceRaw  extends BiboxUSDTSwapBaseService {
    /**
     * Constructor
     *
     * @param exchange
     */
    protected BiboxUSDTSwapTradeServiceRaw(Exchange exchange) {
        super(exchange);
    }

    public String placeBiboxLimitOrder(MHLimitOrder limitOrder) {
        try {
            BiboxUSDTSwapPostionReq cmd =new BiboxUSDTSwapPostionReq(
                    BiboxOrderType.LIMIT_ORDER.asInt(),
                    0,
                    Integer.valueOf(limitOrder.getLeverage()),
                    BiboxOrderSide.fromOrderType(limitOrder.getType()).asInt(),
                    limitOrder.getLimitPrice().toPlainString(),
                    limitOrder.getOriginalAmount().toPlainString(),
                    limitOrder.getParsingCurrencyPair().getParsing(),
                    6,
                    null
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

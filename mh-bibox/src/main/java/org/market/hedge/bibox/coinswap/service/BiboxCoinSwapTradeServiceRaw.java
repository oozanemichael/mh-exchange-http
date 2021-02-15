package org.market.hedge.bibox.coinswap.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.exceptions.ExchangeException;
import org.market.hedge.bibox.BiboxException;
import org.market.hedge.bibox.coinswap.dto.BiboxCoinSwapSingleResponse;

import org.market.hedge.bibox.dto.trade.BiboxOrderSide;
import org.market.hedge.core.ParsingCurrencyPair;
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
            JSONObject json = new JSONObject();
            json.put("pair", limitOrder.getParsingCurrencyPair().getParsing());
            json.put("amount", limitOrder.getOriginalAmount());
            json.put("price", limitOrder.getLimitPrice().toString());
            json.put("order_side", BiboxOrderSide.fromOrderType(limitOrder.getType()).asInt());
            json.put("order_type", 2);
            json.put("order_from", 6);
            if (!StringUtils.isEmpty(limitOrder.getId())) {
                json.put("client_oid", limitOrder.getId());
            }

            String text=json.toJSONString();
            long timestamp = System.currentTimeMillis();
            String sign = BiboxCoinSwapDigest.buildSignature(timestamp + text,exchange.getExchangeSpecification().getSecretKey());
            BiboxCoinSwapSingleResponse<String> response = bibox.trade(text,apiKey,sign,String.valueOf(timestamp));
            throwErrors(response);
            return response.getOrder_id();
        } catch (BiboxException e) {
            throw new ExchangeException(e.getMessage());
        }
    }


    public void cancelAllBibox(ParsingCurrencyPair parsingCurrencyPair) {
        try {
            JSONObject json = new JSONObject();
            json.put("pair", parsingCurrencyPair.getParsing());
            String text=json.toJSONString();
            long timestamp = System.currentTimeMillis();
            String sign = BiboxCoinSwapDigest.buildSignature(timestamp + text,exchange.getExchangeSpecification().getSecretKey());
            BiboxCoinSwapSingleResponse<String> response = bibox.closeAll(text,apiKey,sign,String.valueOf(timestamp));
            throwErrors(response);
        } catch (BiboxException e) {
            throw new ExchangeException(e.getMessage());
        }
    }

}

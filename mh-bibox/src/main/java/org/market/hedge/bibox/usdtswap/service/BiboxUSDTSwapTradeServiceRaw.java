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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class BiboxUSDTSwapTradeServiceRaw  extends BiboxUSDTSwapBaseService {

    private static Logger logger= LoggerFactory.getLogger(BiboxUSDTSwapTradeServiceRaw.class);


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
                    Integer.valueOf(limitOrder.getLeverage()),
                    null,
                    BiboxOrderSide.fromOrderType(limitOrder.getType()).asInt(),
                    limitOrder.getLimitPrice().toPlainString(),
                    limitOrder.getOriginalAmount().toPlainString(),
                    limitOrder.getParsingCurrencyPair().getParsing(),
                    6,
                    Long.parseLong(limitOrder.getId()));
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


    /**
     * 随机生成Long值
     * @param bit 位数
     * @return 返回Long值
     * @throws Exception 异常
     */
    public static Long randomLong(int bit) throws Exception {
        if (bit > 16){
            throw new Exception("bit must <= 16");
        }
        if (bit < 6) {
            throw new Exception("bit must >=6");
        }
        String midStr = "";
        byte[] bytes = UUID.randomUUID().toString().getBytes();
        for (int i = 0; i < bit; i++) {
            midStr += String.valueOf(bytes[i]).toCharArray()[String.valueOf(bytes[i]).toCharArray().length - 1];
        }
        return Long.parseLong(midStr);
    }

}

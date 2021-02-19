package org.market.hedge.deribit.usdtswap;


import org.market.hedge.deribit.usdtswap.dto.trade.req.BitgetUsdtSwapOrderReq;
import org.market.hedge.deribit.usdtswap.dto.trade.resp.BitgetUsdtSwapSingleResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * ACCESS-KEY：API KEY作为一个字符串。
 * ACCESS-SIGN：使用base64编码签名（请参阅签名消息）。
 * ACCESS-TIMESTAMP：您请求的时间戳。
 * ACCESS-PASSPHRASE：您在创建API KEY时设置的口令。
 * Content-Type：统一设置为application/json。
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface BitgetUSwapAuthenticated extends BitgetUSwap {

    /**
     * Create an order (market/limit)
     *
     * @return order id
     * @FormParam
     */
    @POST
    @Path("api/swap/v3/order/batchOrders")
    @Consumes(MediaType.APPLICATION_JSON)
    BitgetUsdtSwapSingleResponse trade(
            BitgetUsdtSwapOrderReq body,
            @HeaderParam(ACCESS_KEY) String apiKey,
            @HeaderParam(ACCESS_SIGN) String signature,
            @HeaderParam(ACCESS_TIMESTAMP) long time,
            @HeaderParam(ACCESS_PASSPHRASE) String passphrase
           );

//    @POST
//    @Path("v3/cbu/order/closeAll")
//    BiboxCoinSwapSingleResponse<String> closeAll(
//            @FormParam("cmd") String body,
//            @HeaderParam(FORM_APIKEY) String apiKey,
//            @HeaderParam(FORM_SIGNATURE) String signature,
//            @HeaderParam(FROM_TIME) String time);
//
//    @POST
//    @Path("v3/cbu/position")
//    BiboxCoinSwapSingleResponse<List<BiboxUSDTSwapPostionResp>> position(
//            @FormParam("cmd") String body,
//            @HeaderParam(FORM_APIKEY) String apiKey,
//            @HeaderParam(FORM_SIGNATURE) String signature,
//            @HeaderParam(FROM_TIME) String time);
}

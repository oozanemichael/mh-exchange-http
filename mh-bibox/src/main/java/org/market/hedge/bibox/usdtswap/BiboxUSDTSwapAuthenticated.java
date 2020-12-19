package org.market.hedge.bibox.usdtswap;

import org.market.hedge.bibox.coinswap.dto.BiboxCoinSwapSingleResponse;
import org.market.hedge.bibox.dto.BiboxSingleResponse;
import si.mazi.rescu.ParamsDigest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * auth api
 * */
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public interface BiboxUSDTSwapAuthenticated extends BiboxUSDTSwap{

    /**
     * Create an order (market/limit)
     *
     * @return order id
     */
    @POST
    @Path("v3.1/ctrade/order/open")
    BiboxCoinSwapSingleResponse<String> trade(
            @FormParam("cmd") String body,
            @HeaderParam(FORM_APIKEY) String apiKey,
            @HeaderParam(FORM_SIGNATURE) String signature,
            @HeaderParam(FROM_TIME) String time);


}

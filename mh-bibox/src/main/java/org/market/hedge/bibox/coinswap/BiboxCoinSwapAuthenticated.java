package org.market.hedge.bibox.coinswap;

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
public interface BiboxCoinSwapAuthenticated extends BiboxCoinSwap{

    /**
     * Create an order (market/limit)
     *
     * @return order id
     */
    @POST
    @Path("orderpending")
    BiboxSingleResponse<String> trade(
            @FormParam(FORM_CMDS) String cmds,
            @FormParam(FORM_APIKEY) String apiKey,
            @FormParam(FORM_SIGNATURE) ParamsDigest signature);


}

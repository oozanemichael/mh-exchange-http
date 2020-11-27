package org.market.hedge.deribit.futures;

import org.market.hedge.deribit.dto.marketdata.results.HuobiDepthResult;
import org.market.hedge.deribit.futures.dto.account.HuobiFuturesPositionInfoRequest;
import org.market.hedge.deribit.futures.dto.account.results.HuobiFuturesPositionInfoResult;
import org.market.hedge.deribit.futures.dto.trader.HuobiFuturesCreateBatchOrderRequest;
import org.market.hedge.deribit.option.dto.trader.results.HuobiOptionPlaceOrdersResult;
import si.mazi.rescu.ParamsDigest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface HuobiFutures {

    @GET
    @Path("market/depth")
    HuobiDepthResult getDepth(@QueryParam("symbol") String symbol, @QueryParam("type") String type)
            throws IOException;


    @POST
    @Path("api/v1/contract_batchorder")
    @Consumes(MediaType.APPLICATION_JSON)
    HuobiOptionPlaceOrdersResult placeLimitOrders(
            HuobiFuturesCreateBatchOrderRequest body,
            @QueryParam("AccessKeyId") String apiKey,
            @QueryParam("SignatureMethod") String signatureMethod,
            @QueryParam("SignatureVersion") int signatureVersion,
            @QueryParam("Timestamp") String nonce,
            @QueryParam("Signature") ParamsDigest signature)
            throws IOException;

    @POST
    @Path("api/v1/contract_position_info")
    @Consumes(MediaType.APPLICATION_JSON)
    HuobiFuturesPositionInfoResult contractPositionInfo(
            HuobiFuturesPositionInfoRequest body,
            @QueryParam("AccessKeyId") String apiKey,
            @QueryParam("SignatureMethod") String signatureMethod,
            @QueryParam("SignatureVersion") int signatureVersion,
            @QueryParam("Timestamp") String nonce,
            @QueryParam("Signature") ParamsDigest signature
    )throws IOException;


}

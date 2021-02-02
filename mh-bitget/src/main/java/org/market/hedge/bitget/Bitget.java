package org.market.hedge.bitget;

import org.market.hedge.bitget.usdtswap.dto.marketdata.resq.BitgeUSwapDepthResult;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * @author chuxianbo
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface Bitget {

    @GET
    @Path("api/swap/v3/market/depth")
    BitgeUSwapDepthResult getDepth(@QueryParam("symbol") String symbol, @QueryParam("limit") Integer limit) throws IOException;

}

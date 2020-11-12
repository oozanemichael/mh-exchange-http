package org.market.hedge.bibox.coinswap;

import org.market.hedge.bibox.BiboxException;
import org.market.hedge.bibox.dto.BiboxResponse;
import org.market.hedge.bibox.dto.trade.BiboxOrderBook;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface BiboxCoinSwap {

    /**
     * Retrieves the order book for a currency pair.
     *
     * pair	true	string	pair		4BTC_USDT, 4ETH_USDT, ...
     * size	false	integer	size	200	1-200
     * @return
     * @throws IOException
     * @throws BiboxException
     */
    @GET
    @Path("v2/mdata/depth")
    BiboxResponse<BiboxOrderBook> orderBook(
            @QueryParam("pair") String pair,
            @QueryParam("size") Integer size)
            throws IOException, BiboxException;

}

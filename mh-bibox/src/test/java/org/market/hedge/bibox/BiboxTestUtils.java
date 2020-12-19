package org.market.hedge.bibox;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import org.market.hedge.bibox.dto.account.BiboxAccountUnmarshalTest;

public class BiboxTestUtils {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  public static <R> R getResponse(TypeReference<R> ref, String resourcePath)
      throws JsonParseException, JsonMappingException, IOException {
    InputStream is = BiboxAccountUnmarshalTest.class.getResourceAsStream(resourcePath);
    return MAPPER.readValue(is, ref);
  }
}

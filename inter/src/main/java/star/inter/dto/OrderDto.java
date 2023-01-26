package star.inter.dto;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

public class OrderDto {

    @Data
    public static class In {
        @Schema(example = "altm56", description = "아이디")
        private String userId;
        @Schema(example = "아이스아메리카노", description = "상품이름")
        private String orderName;
    }

    @Data
    public static class Out {
        @Schema(example = "성공!", description = "성공 메세지")
        private String data;

    }

    @Data
    @Builder
    public static class Result {
        @Builder.Default
        @Schema(example = "200")
        private Integer resultCode = 200;

        @Builder.Default
        @Schema(example = "OK")
        private String resultMsg = "OK";

        @Schema(implementation = OrderDto.Out.class)
        public Map<String,Object> data;
    }
    
}

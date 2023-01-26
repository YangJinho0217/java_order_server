package star.inter.dto;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

public class OrderListDto {

    @Data
    public static class In {
        @Schema(example = "altm56", description = "아이디")
        private String userId;
    }

    @Data
    public static class Out {
        @Schema(example = "1", description = "아이디")
        private String id;
        @Schema(example = "altm56", description = "아이디")
        private String userId;
        @Schema(example = "아메리카노", description = "상품이름")
        private String orderName;
        @Schema(example = "날짜", description = "2023-01-16")
        private String rgstDt;

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

        @Schema(implementation = OrderListDto.Out.class)
        public Map<String,Object> data;
    }
}

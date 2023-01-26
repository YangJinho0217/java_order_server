package star.inter.dto;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

public class SignUpDto {

    @Data
    public static class In {
        @Schema(example = "altm56", description = "아이디")
        private String userId;
        @Schema(example = "1111", description = "비밀번호")
        private String passwd;
        @Schema(example = "왕눈이", description = "닉네임")
        private String userName;
        @Schema(example = "사원", description = "직급")
        private String userRank;
        @Schema(example = "504", description = "호실")
        private String userRoom;
        @Schema(example = "01000000000", description = "전화번호")
        private String userPhone;
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

        @Schema(implementation = SignUpDto.Out.class)
        public Map<String,Object> data;
    }
}

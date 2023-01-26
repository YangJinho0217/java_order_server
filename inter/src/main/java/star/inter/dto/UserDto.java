package star.inter.dto;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
public class UserDto {

    @Data
    public static class In {
        @Schema(example = "altm56", description = "유저 아이디")
        private String userId;
        @Schema(example = "1111", description = "비밀번호")
        private String passwd;
    }

    @Data
    public static class Out {
        @Schema(example = "227", description = "유저 아이디")
        private Integer userId;
        @Schema(example = "1111", description = "유저 패스워드")
        private String userPassword;
        @Schema(example = "진호", description = "유저 이름")
        private String userName;
        @Schema(example = "사원", description = "직급")
        private String userRank;
        @Schema(example = "504", description = "유저 방번호")
        private String userRoom;
        @Schema(example = "01000000000", description = "전화번호")
        private String userPhone;
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

        @Schema(implementation = UserDto.Out.class)
        public Map<String,Object> data;
    }
}

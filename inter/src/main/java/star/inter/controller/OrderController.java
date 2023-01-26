package star.inter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import star.inter.dto.OrderDto;
import star.inter.dto.OrderListDto;
import star.inter.dto.OrderTDListDto;
import star.inter.dto.SignUpDto;
import star.inter.dto.UserDto;
import star.inter.service.OrderService;
import star.inter.service.UserService;

@RestController
@RequestMapping("/order")
@Tag(name = "order", description = "주문하기")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "상품주문리스트", description = "상품 주문확인 시 호출", tags = { "order" })
    @ApiResponses(value = {
            @ApiResponse(description = "OK", responseCode = "200", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderListDto.Result.class)) }),
    })

    @PostMapping(value = "list", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> orderlist(@Parameter OrderListDto.In in) {
        return new ResponseEntity<>(orderService.orderlist(in), HttpStatus.OK);
    }

    @Operation(summary = "상품주문", description = "상품 주문 시 호출", tags = { "order" })
    @ApiResponses(value = {
            @ApiResponse(description = "OK", responseCode = "200", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderDto.Result.class)) }),
    })

    @PostMapping(value = "order", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> order(@Parameter OrderDto.In in) {
        return new ResponseEntity<>(orderService.order(in), HttpStatus.OK);
    }

    @Operation(summary = "오늘의 주문 내역", description = "오늘의 주문내역확인 시 호출", tags = { "order" })
    @ApiResponses(value = {
            @ApiResponse(description = "OK", responseCode = "200", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderTDListDto.Result.class)) }),
    })

    @GetMapping(value = "tdOrder")
    public ResponseEntity<?> tdOrder() {
        return new ResponseEntity<>(orderService.tdOrder(), HttpStatus.OK);
    }
}


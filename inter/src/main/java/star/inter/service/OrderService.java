package star.inter.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import star.inter.dao.OrderDao;
import star.inter.dao.UserDao;
import star.inter.dto.OrderDto;
import star.inter.dto.OrderListDto;
import star.inter.dto.OrderTDListDto;
import star.inter.dto.SignUpDto;
import star.inter.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public OrderListDto.Result orderlist(OrderListDto.In in) {

        OrderListDto.Out out = new OrderListDto.Out();

        Map<String, Object> param = new HashMap<String,Object>();
        param = om.convertValue(in, new TypeReference<Map<String,Object>>(){});
        Map<String,Object> result = new HashMap<String,Object>();

        Map<String,Object> orderList = orderDao.selectOrderList(param);

        // out = om.convertValue(orderList, OrderListDto.Out.class);
        if (orderList == null) {
            result.put("error", "주문정보가 없습니다.");
            return OrderListDto.Result.builder().data(result).build();
        }
        result.put("orderList", orderList);

        return OrderListDto.Result.builder().data(orderList).build();
    }
    
    public OrderDto.Result order(OrderDto.In in) {

        Map<String, Object> param = new HashMap<String,Object>();
        param = om.convertValue(in, new TypeReference<Map<String,Object>>(){});
        Map<String,Object> result = new HashMap<String,Object>();

        Map<String,Object> appId = userDao.selectAppId(param);

        if (appId == null) {
            result.put("error", "아이디가 존재하지 않습니다.");
            return OrderDto.Result.builder().data(result).build();
        }

        orderDao.insertOrder(param);
        result.put("order", "주문이 완료되었습니다");
        return OrderDto.Result.builder().data(result).build();
    }

    public OrderTDListDto.Result tdOrder() {

        OrderTDListDto.Out out = new OrderTDListDto.Out();

        Map<String, Object> param = new HashMap<String,Object>();
        // param = om.convertValue(in, new TypeReference<Map<String,Object>>(){});
        Map<String,Object> result = new HashMap<String,Object>();

        List<Map<String,Object>> todayOrderList = orderDao.selectTodayOrderList();
        if (todayOrderList.isEmpty()) {
            result.put("error", "조회된 정보가 없습니다");
            return OrderTDListDto.Result.builder().data(result).build();
        }
        
        result.put("list", todayOrderList);
        return OrderTDListDto.Result.builder().data(result).build();
    }

    
    
}

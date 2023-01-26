package star.inter.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao {
    public Map<String,Object> selectOrderList(Map<String,Object> param);
    public void insertOrder(Map<String,Object> param);
    public List<Map<String,Object>> selectTodayOrderList();
}

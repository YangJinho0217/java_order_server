package star.inter.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
   public Map<String,Object> selectAppLogin(Map<String,Object> param);
   public void insertAppSignUp(Map<String,Object> param);
   public Map<String,Object> selectAppId(Map<String,Object> param);
}


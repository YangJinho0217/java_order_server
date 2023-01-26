package star.inter.service;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import star.inter.dao.UserDao;
import star.inter.dto.SignUpDto;
import star.inter.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;

    ObjectMapper om = new ObjectMapper();


    public UserDto.Result login(UserDto.In in) {

        UserDto.Out out = new UserDto.Out();

        Map<String, Object> param = new HashMap<String,Object>();
        param = om.convertValue(in, new TypeReference<Map<String,Object>>(){});
        Map<String,Object> result = new HashMap<String,Object>();

        Map<String,Object> user = userDao.selectAppLogin(param);
        if (user == null) {
            result.put("user", "유저 정보가 없습니다");
            return UserDto.Result.builder().data(result).build();
        }

        Object passwordData = user.get("userPassword");
        String passwordDb = (String) param.get("passwd");

        if(!passwordData.equals(passwordDb)) {
            result.put("error", "비밀번호가 일치하지 않습니다.");
            return UserDto.Result.builder().data(result).build();
        }

        result.put("user", user);
        return UserDto.Result.builder().data(user).build();
    }

    public SignUpDto.Result signUp(SignUpDto.In in) {

        Map<String, Object> param = new HashMap<String,Object>();
        param = om.convertValue(in, new TypeReference<Map<String,Object>>(){});
        Map<String,Object> result = new HashMap<String,Object>();

        if (param.get("userId") == null) {
            result.put("error", "아이디를 입력해 주세요");
            return SignUpDto.Result.builder().data(result).build();
        } else if(param.get("passwd") == null) {
            result.put("error", "비밀번호를 입력해 주세요");
            return SignUpDto.Result.builder().data(result).build();
        } else if(param.get("userName") == null) {
            result.put("error", "닉네임을 입력해 주세요");
            return SignUpDto.Result.builder().data(result).build();
        } else if(param.get("userRank") == null) {
            result.put("error", "직급을 입력해 주세요");
            return SignUpDto.Result.builder().data(result).build();
        } else if(param.get("userRoom") == null) {
            result.put("error", "방번호를 입력해 주세요");
            return SignUpDto.Result.builder().data(result).build();
        }

        Map<String,Object> appId = userDao.selectAppId(param);

        if (appId != null) {
            result.put("error", "사용할 수 없는 아이디 입니다");
            return SignUpDto.Result.builder().data(result).build();
        } 

        userDao.insertAppSignUp(param);
        result.put("user", "회원가입에 성공하였습니다");
        return SignUpDto.Result.builder().data(result).build();
    }
    

}

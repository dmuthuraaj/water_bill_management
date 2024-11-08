package com.opzero.user.mapper;

import org.springframework.beans.BeanUtils;

import com.opzero.user.dto.response.UserDetailResponse;
import com.opzero.user.dto.response.UserSummaryResponse;
import com.opzero.user.mongo.entity.User;

public class UserMapper {

    public static UserSummaryResponse toSummaryResponse(User user) {
        UserSummaryResponse response = new UserSummaryResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }

    public static UserDetailResponse toDetailResponse(User user) {
        UserDetailResponse response = new UserDetailResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }
}

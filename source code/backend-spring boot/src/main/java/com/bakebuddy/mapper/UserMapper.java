package com.bakebuddy.mapper;

import com.bakebuddy.dto.OrderDto;
import com.bakebuddy.dto.OrderItemDto;
import com.bakebuddy.dto.UserDto;
import com.bakebuddy.model.Order;
import com.bakebuddy.model.OrderItem;
import com.bakebuddy.model.User;

public class UserMapper {

    public static UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

}

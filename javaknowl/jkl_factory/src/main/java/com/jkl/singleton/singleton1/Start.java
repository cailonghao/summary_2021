package com.jkl.singleton.singleton1;

import com.jkl.pojo.UserDto;
import org.springframework.beans.BeanUtils;

public class Start {

    public static void main(String[] args) {
//        for (int i=0;i <10;i++){
//            new Thread(new Runnable() {
//                public void run() {
//                    //System.out.println(Single1.getInstance());
//                    System.out.println(Single2.getInstance());
//                }
//            }).start();
//        }

        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setName("name");
        UserDto temp1 = new UserDto();
        BeanUtils.copyProperties(userDto, temp1);
        userDto.setId(10);
        System.out.println(temp1);
        System.out.println(userDto);
    }
}

package com.example.springboottest.test;

import com.example.springboottest.dto.UserDetailDto;
import org.apache.commons.lang3.time.StopWatch;

import java.util.*;

public class BitTest {
    public static void main(String[] args) {
        List<UserDetailDto> list = new ArrayList<>();
        List<UserDetailDto> list2 = new ArrayList<>();
        List<UserDetailDto> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        BitSet bitSet = new BitSet();

        for (int i = 0; i < 100000; i++) {
            UserDetailDto userDetailDto = new UserDetailDto();
            userDetailDto.setUserId(i);
            userDetailDto.setDetailName("user"+i);
            list.add(userDetailDto);
        }
        for (int i = 1000; i < 100000; i++) {
            UserDetailDto userDetailDto = new UserDetailDto();
            userDetailDto.setUserId(i);
            userDetailDto.setDetailName("user"+i);
            list2.add(userDetailDto);
            set.add(i);
            bitSet.set(i);
        }
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        /*list.stream().parallel().forEach(e ->{
            list2.stream().parallel().forEach(e2 ->{
                if(e.getUserId().equals(e2.getUserId())){
                    result.add(e2);
                }
            });
        });
        System.out.println("list:"+stopWatch.getTime());*/

        stopWatch.reset();
        stopWatch.start();
        list.forEach(e ->{
            if(set.contains(e.getDetailName())){
                  result.add(e);
            }
        });
        System.out.println("set:"+stopWatch.getTime());

        stopWatch.reset();
        stopWatch.start();
        list.stream().parallel().forEach(e ->{
            if(bitSet.get(e.getUserId())){
                result.add(e);
            }
        });
        System.out.println("bitSet:"+stopWatch.getTime());

    }
}

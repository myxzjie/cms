package com.xzjie.cms;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class DateTest {
    @Test
    public void time(){
        System.out.println(LocalDateTime.now().minusHours(2));
    }
}

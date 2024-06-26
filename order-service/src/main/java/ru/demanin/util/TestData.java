package ru.demanin.util;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestData {
    private long id;

    private String secretPaymentUrl;

    private String estimatedTimeOfArrival;

    public TestData get(){
        return new TestData(1,"Oplachen","10min");
    }
}

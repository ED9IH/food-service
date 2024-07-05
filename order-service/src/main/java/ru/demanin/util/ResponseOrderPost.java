package ru.demanin.util;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseOrderPost {

    private long id;

    private String secretPaymentUrl;

    private String estimatedTimeOfArrival;

    public ResponseOrderPost get(){
        return new ResponseOrderPost(1,"Oplachen","10min");
    }
}

package ru.demanin.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RabbitMessage {
    private long orderId;

    private String queueToSend;

    private String message;
}

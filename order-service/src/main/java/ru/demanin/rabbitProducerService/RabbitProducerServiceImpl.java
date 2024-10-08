package ru.demanin.rabbitProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.demanin.RabbitProducerService.RabbitProducerService;

@Service
@RequiredArgsConstructor
public class RabbitProducerServiceImpl implements RabbitProducerService {
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(String message, String routingKey) {
        rabbitTemplate.convertAndSend("directExchange", routingKey, message);

    }
}

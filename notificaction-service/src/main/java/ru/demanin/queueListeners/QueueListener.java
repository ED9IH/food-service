package ru.demanin.queueListeners;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.demanin.dto.RabbitMessage;
import ru.demanin.rabbitProducerServices.RabbitProducerServiceImpl;

@Service
@RequiredArgsConstructor
public class QueueListener {
    private final RabbitProducerServiceImpl rabbitProducerService;
    private final ObjectMapper mapper;

    @RabbitListener(queues = "notificationQueue")
    public void processMyQueue(String message) throws JsonProcessingException {

        RabbitMessage rabbitMessage = mapper.readValue(message, RabbitMessage.class);

        String queueToSend = rabbitMessage.getQueueToSend();

        System.out.println(rabbitMessage);

        rabbitProducerService.sendMessage(message, queueToSend);

    }
}

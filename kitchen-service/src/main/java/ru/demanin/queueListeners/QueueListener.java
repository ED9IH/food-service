package ru.demanin.queueListeners;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.demanin.dto.RabbitMessage;
import ru.demanin.rabbitProducerServices.RabbitProducerServiceImpl;
import ru.demanin.service.KitchenService;

@Service
@RequiredArgsConstructor
public class QueueListener {
    private final RabbitProducerServiceImpl rabbitProducerService;
    private final ObjectMapper mapper;

    @RabbitListener(queues = "kitchenQueue")
    public void processMyQueue(String message) throws JsonProcessingException {

        RabbitMessage rabbitMessage = mapper.readValue(message, RabbitMessage.class);

        String queueToSend = rabbitMessage.getQueueToSend();

        rabbitProducerService.sendMessage(message, queueToSend);

    }
}

package ru.demanin.rabbitProducerService;

public interface RabbitProducerService {
    void sendMessage(String message, String routingKey);
}

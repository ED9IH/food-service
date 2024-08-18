package ru.demanin.RabbitProducerService;

public interface RabbitProducerService {
    void sendMessage(String message, String routingKey);
}

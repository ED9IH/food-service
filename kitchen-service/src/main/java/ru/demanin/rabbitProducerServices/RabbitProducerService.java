package ru.demanin.rabbitProducerServices;

public interface RabbitProducerService {
    void sendMessage(String message, String routingKey);
}

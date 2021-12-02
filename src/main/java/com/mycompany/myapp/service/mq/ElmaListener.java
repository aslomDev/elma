package com.mycompany.myapp.service.mq;

import com.mycompany.myapp.service.CrudService;
import com.mycompany.myapp.service.dto.entityDto.HumoCardDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Component
public class ElmaListener {

    private final RabbitTemplate template;
    private final CrudService crudService;

    public ElmaListener(RabbitTemplate template, CrudService crudService) {
        this.template = template;
        this.crudService = crudService;
    }

    @Transactional
    @RabbitListener(queues = ElmaToHumo.QUEUE)
    public Mono<Void> elmaLiten(HumoCardDTO cardDTO){
        return crudService.save(cardDTO).flatMap(params -> {
            return Mono.empty();
        });
    }
}

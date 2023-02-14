package com.exemplo.ms.msemail.consumers;

import ch.qos.logback.core.CoreConstants;
import com.exemplo.ms.msemail.dtos.EmailDto;
import com.exemplo.ms.msemail.models.EmailModel;
import com.exemplo.ms.msemail.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDto emailDto){
        EmailModel emailModel =  new EmailModel();

        BeanUtils.copyProperties(emailDto, emailModel );
        emailService.sendEmail(emailModel);
        System.out.println("Email status: "+emailModel.getStatusEmail().toString());
    }


}

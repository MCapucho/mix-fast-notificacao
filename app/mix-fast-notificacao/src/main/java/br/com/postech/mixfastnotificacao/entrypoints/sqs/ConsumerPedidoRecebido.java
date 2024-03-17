package br.com.postech.mixfastnotificacao.entrypoints.sqs;

import jakarta.jms.TextMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerPedidoRecebido {

    @JmsListener(destination = "${cloud.aws.sqs.name}")
    public void consumerNotificacaoPedido(TextMessage textMessage) {
        System.out.println(textMessage);
    }
}

package br.com.postech.mixfastnotificacao.entrypoints.sqs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.List;

@Service
public class ConsumerPedidoRecebido {

    @Value("${aws.queue.url}")
    private String queueUrl;

    Region region = Region.US_EAST_1;

    SqsClient sqsClient = SqsClient.builder()
            .region(region)
            .build();

    public void consumerNotificacaoPedido() {
        try {
            ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .maxNumberOfMessages(5)
                    .build();

            List<Message> listaMensagens = sqsClient.receiveMessage(receiveMessageRequest).messages();
            System.out.println(listaMensagens);
        } catch (SqsException e) {
            System.out.println(e.getMessage());
        }
    }
}

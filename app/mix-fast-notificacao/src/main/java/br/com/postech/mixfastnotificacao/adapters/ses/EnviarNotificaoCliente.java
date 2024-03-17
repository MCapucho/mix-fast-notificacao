package br.com.postech.mixfastnotificacao.adapters.ses;

import br.com.postech.mixfastnotificacao.dto.NotificacaoRequest;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Service
public class EnviarNotificaoCliente {

    SesClient sesClient = SesClient.builder()
            .region(Region.US_EAST_1)
            .build();

    public void enviarEmail(NotificacaoRequest notificacaoRequest) {
        Destination destination = Destination.builder()
                .toAddresses(notificacaoRequest.getEmailCliente())
                .build();

        Content content = Content.builder()
                .data("Teste")
                .build();

        Content sub = Content.builder()
                .data(String.format("Notificação Mix-Fast - Pedido nº %s", notificacaoRequest.getCodigoPedido()))
                .build();

        Body body = Body.builder()
                .html(content)
                .build();

        Message msg = Message.builder()
                .subject(sub)
                .body(body)
                .build();

        SendEmailRequest emailRequest = SendEmailRequest.builder()
                .destination(destination)
                .message(msg)
                .source("murylo_capucho@outlook.com")
                .build();

        try {
            sesClient.sendEmail(emailRequest);
        } catch (SesException e) {
            System.out.println(e.getMessage());
        }
    }
}

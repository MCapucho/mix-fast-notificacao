package br.com.postech.mixfastnotificacao.adapters.ses;

import br.com.postech.mixfastnotificacao.dto.NotificacaoRequest;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EnviarNotificaoCliente {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    public void enviarEmail(NotificacaoRequest notificacaoRequest) {
        try {
            SendEmailRequest sendEmailRequest = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(notificacaoRequest.getEmailCliente()))
                    .withMessage(
                            new Message().withBody(
                                    new Body().withHtml(
                                            new Content()
                                                    .withCharset("UTF-8")
                                                    .withData("Teste")))
                                    .withSubject(
                                            new Content()
                                                    .withCharset("UTF-8")
                                                    .withData(String.format("Notificação MixFast - Pedido nº %s", notificacaoRequest.getCodigoPedido()))))
                            .withSource("murylo_capucho@outlook.com");
            SendEmailResult result = amazonSimpleEmailService.sendEmail(sendEmailRequest);
            System.out.println(result.getMessageId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

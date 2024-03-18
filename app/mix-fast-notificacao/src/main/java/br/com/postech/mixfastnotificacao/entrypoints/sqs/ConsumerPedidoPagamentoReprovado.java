package br.com.postech.mixfastnotificacao.entrypoints.sqs;

import br.com.postech.mixfastnotificacao.adapters.ses.EnviarNotificaoCliente;
import br.com.postech.mixfastnotificacao.dto.NotificacaoRequest;
import com.google.gson.Gson;
import jakarta.jms.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ConsumerPedidoPagamentoReprovado {

    private final Gson gson;
    private final EnviarNotificaoCliente enviarNotificaoCliente;

    @SneakyThrows
    @JmsListener(destination = "${aws.queue.name.reprovado}")
    public void consumerNotificacaoPedido(TextMessage textMessage) {
        NotificacaoRequest notificacaoRequest = gson.fromJson(textMessage.getText(), NotificacaoRequest.class);
        System.out.println(notificacaoRequest);
        enviarNotificaoCliente.enviarEmail(notificacaoRequest);
    }
}

package mapbegoodchat.chat.handler;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import mapbegoodchat.chat.jwt.JwtTokenizer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;

@Slf4j
@RequiredArgsConstructor
@Component
public class StompHandler implements ChannelInterceptor {
    private final JwtTokenizer jwtTokenProvider;

    @SneakyThrows
    @Override
    public Message preSend(Message message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        if(accessor.getCommand() == StompCommand.CONNECT) {

            log.error(accessor.toString());
            try {
                jwtTokenProvider.validateToken(accessor.getFirstNativeHeader("Authorization"));
            } catch (Exception e) {
                log.error("Token 인증 실패: " + e.getMessage());
                throw new AccessDeniedException("Invalid Token");
            }
        }
        return message;
    }
}

package mapbegoodchat.chat.mapper;

import mapbegoodchat.chat.dto.MessageDTO;
import mapbegoodchat.chat.entity.Message;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MessageMapper {
    public Message dtoToMessage(MessageDTO messageDto) {
        if (Objects.isNull(messageDto)) return null;

        return Message.builder()
                .groupId(messageDto.getGroupId())
                .profileImage(messageDto.getProfileImage())
                .sender(messageDto.getSender())
                .message(messageDto.getMessage())
                .build();
    }
}

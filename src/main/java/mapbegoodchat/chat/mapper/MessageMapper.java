package mapbegoodchat.chat.mapper;

import mapbegoodchat.chat.dto.MessageDTO;
import mapbegoodchat.chat.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    public Message dtoToMessage(MessageDTO messageDto) {
        return Message.builder()
                .groupId(messageDto.getGroupId())
                .sender(messageDto.getSender())
                .message(messageDto.getMessage())
                .build();
    }
}

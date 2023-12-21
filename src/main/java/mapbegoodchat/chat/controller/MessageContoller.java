package mapbegoodchat.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mapbegoodchat.chat.dto.MessageDTO;
import mapbegoodchat.chat.dto.PlaceDTO;
import mapbegoodchat.chat.entity.Message;
import mapbegoodchat.chat.mapper.MessageMapper;
import mapbegoodchat.chat.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MessageContoller {
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final MessageMapper mapper;
    private final MessageService service;

    @Transactional
    @MessageMapping("/msg")
    public void message(MessageDTO messageDto) throws Exception {
        Message message = mapper.dtoToMessage(messageDto);
        service.save(message);
        simpMessageSendingOperations.convertAndSend("/topic/" + messageDto.getGroupId(), messageDto);
    }

    @MessageMapping("/place")
    public void PlaceDTO(PlaceDTO placeDto) throws Exception {
        simpMessageSendingOperations.convertAndSend("/topic/" + placeDto.getGroupId(), placeDto);
    }

    @GetMapping("/msg/{groupId}")
    public ResponseEntity groupMessage(@PathVariable("groupId") Long groupId,
                                       @RequestParam(required = false) Long page) {
        List<Message> messageList;
        if(Objects.isNull(page)) {
            messageList = service.messageList(groupId, 1);
        } else {
            messageList = service.messageList(groupId, page.intValue());
        }
        log.error(String.valueOf(messageList.size()));
        return new ResponseEntity<>(messageList, HttpStatus.OK);
    }

    @GetMapping("/msg-all/{groupId}")
    public ResponseEntity groupAllMessage(@PathVariable("groupId") Long groupId) {
        List<Message> messageList = service.messageAll(groupId);
        log.error("Message All Size: " + messageList.size());
        return new ResponseEntity<>(messageList, HttpStatus.OK);
    }
}

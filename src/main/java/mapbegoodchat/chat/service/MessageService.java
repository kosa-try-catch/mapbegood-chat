package mapbegoodchat.chat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mapbegoodchat.chat.entity.Message;
import mapbegoodchat.chat.repository.MessageRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageService {
    private final MessageRepository repository;

    public void save(Message message) {
        repository.save(message);
    }

    public List<Message> messageList(Long groupId, int page) {
        return repository.findByGroupId(
                 groupId,
                PageRequest.of(page-1, 50, Sort.by("sendAt").descending())
        );
    }

    public List<Message> messageAll(Long groupId) {
        return repository.findAllByGroupId(groupId);
    }
}

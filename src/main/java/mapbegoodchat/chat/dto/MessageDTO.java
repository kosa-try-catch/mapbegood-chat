package mapbegoodchat.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private Long groupId;
    private String sender;
    private String message;
    private LocalDateTime sendAt;
}

package mapbegoodchat.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private Long groupId;
    private String profileImage;
    private String sender;
    private String message;
//    private LocalDateTime sendAt;
}

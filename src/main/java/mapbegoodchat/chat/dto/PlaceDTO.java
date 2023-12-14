package mapbegoodchat.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mapbegoodchat.chat.entity.Place;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDTO {
    private String groupId;
    private String email;
    private String nickName;
    private Place place;
}

package mapbegoodchat.chat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Place {
    private String placeName;
    private String address;
    private Double x;
    private Double y;
    private String category;
}

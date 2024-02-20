package courseonline4399.online.constants;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDTO {
    private String contents;
    private int userid;
    private int courseid;
    private Date createddate;
    private boolean status;

}

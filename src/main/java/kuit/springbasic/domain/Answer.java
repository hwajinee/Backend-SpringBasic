package kuit.springbasic.domain;


import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Answer {
    private int answerId;
    private int questionId;
    private String writer;
    private String contents;
    private Date createdDate;

    public Answer(int questionId, String writer, String contents) {
        this.questionId = questionId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = Date.valueOf(LocalDate.now());
    }

}

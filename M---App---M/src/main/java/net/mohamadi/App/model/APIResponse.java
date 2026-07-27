package net.mohamadi.App.model;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class APIResponse<T> {

    //برای وقتی که دیتای ما خطا دارد
    private String message;
    private HttpStatus status;
    private T data;

}

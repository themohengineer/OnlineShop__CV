package net.mohamadi.App.model;


import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class APIResponse<T> {

    //برای وقتی که دیتای ما خطا دارد
    private String message;
    private HttpStatus status;
    private T data;

}

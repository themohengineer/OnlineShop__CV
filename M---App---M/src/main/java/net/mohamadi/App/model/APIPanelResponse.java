package net.mohamadi.App.model;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class APIPanelResponse<T> extends APIResponse<T> {

    private Long totalCount=0L;//I mean, how much data do we have?  for pagination
    private Integer totalPages=0;

//    public APIPanelResponse(String meesage,
//                            HttpStatus status,
//                            T data,
//                            Long totalCount,
//                            Integer totalPages) {
//        super(meesage, status, data);
//        this.totalCount = totalCount;
//        this.totalPages = totalPages;
//
//    }
}

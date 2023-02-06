package com.example.springserver2;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SwipeDetails {
    @JsonProperty
    private String swiper;
    @JsonProperty
    private String swipee;
    @JsonProperty
    private String comment;
}

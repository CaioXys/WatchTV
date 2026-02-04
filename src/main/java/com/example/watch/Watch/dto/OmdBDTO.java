package com.example.watch.Watch.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OmdBDTO {

    private String title;
    private String year;
    private String rated;
    private String released;
    private String runtime;

}

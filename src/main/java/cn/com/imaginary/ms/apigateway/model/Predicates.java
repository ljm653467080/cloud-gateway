package cn.com.imaginary.ms.apigateway.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Predicates {

    @JsonProperty("args")
    private ArgsDTO args;
    @JsonProperty("name")
    private String name;

    @NoArgsConstructor
    @Data
    public static class ArgsDTO {
        @JsonProperty("pattern")
        private String pattern;
        @JsonProperty("pattern1")
        private String pattern1;
    }
}

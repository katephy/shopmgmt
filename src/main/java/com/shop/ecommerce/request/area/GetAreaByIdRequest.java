package com.shop.ecommerce.request.area;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAreaByIdRequest {

    @JsonProperty("areaId")
    long areaId;

    public GetAreaByIdRequest() {

    }
}

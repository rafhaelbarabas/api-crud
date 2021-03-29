package com.rafhael.barabas.crud.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rafhael.barabas.crud.entities.Product;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"id", "name", "inventory", "price"})
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductVO extends RepresentationModel<ProductVO> implements Serializable {

    private static final long serialVersionUID = -5957199924082509780L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("inventory")
    private Integer inventory;

    @JsonProperty("price")
    private Double price;

    public static ProductVO create(Product product) {
        return new ModelMapper().map(product, ProductVO.class);
    }
}

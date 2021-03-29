package com.rafhael.barabas.crud.entities;

import com.rafhael.barabas.crud.data.vo.ProductVO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Product implements Serializable {

    private static final long serialVersionUID = 4052230431750384577L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "inventory", nullable = false)
    private Integer inventory;

    @Column(name = "price", nullable = false)
    private Double price;

    public static Product create(ProductVO productVO) {
        return new ModelMapper().map(productVO, Product.class);
    }

}

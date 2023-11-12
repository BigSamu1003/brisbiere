package com.brisbiere.Brisbiere.persistence.mapper;


import com.brisbiere.Brisbiere.domain.Product;
import com.brisbiere.Brisbiere.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductOrderMapper.class})
public interface ProductMapper {
    @Mappings({
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombreProducto", target = "name"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "descripcion", target = "description"),
            @Mapping(source = "marca", target = "brand"),
            @Mapping(source = "estilo", target = "style"),
            @Mapping(source = "imagen", target = "image"),
            @Mapping(source = "precio", target = "price"),
            @Mapping(source = "idCervecero", target = "brewerId"),
    })
    Product toProduct(Producto producto);
    List<Product> toProducts(List<Producto> productos);


    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "cervecero", ignore = true)
    })
    Producto toProducto(Product product);
}


package com.brisbiere.Brisbiere.persistence.mapper;

import com.brisbiere.Brisbiere.domain.Product;
import com.brisbiere.Brisbiere.persistence.entity.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-03T16:35:32-0600",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.2.1.jar, environment: Java 17.0.4 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(Producto producto) {
        if ( producto == null ) {
            return null;
        }

        Product product = new Product();

        product.setProductId( producto.getIdProducto() );
        product.setName( producto.getNombreProducto() );
        if ( producto.getCantidadStock() != null ) {
            product.setStock( producto.getCantidadStock() );
        }
        product.setDescription( producto.getDescripcion() );
        product.setBrand( producto.getMarca() );
        product.setStyle( producto.getEstilo() );
        product.setImage( producto.getImagen() );
        product.setPrice( producto.getPrecio() );
        if ( producto.getIdCervecero() != null ) {
            product.setBrewerId( producto.getIdCervecero() );
        }

        return product;
    }

    @Override
    public List<Product> toProducts(List<Producto> productos) {
        if ( productos == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( productos.size() );
        for ( Producto producto : productos ) {
            list.add( toProduct( producto ) );
        }

        return list;
    }

    @Override
    public Producto toProducto(Product product) {
        if ( product == null ) {
            return null;
        }

        Producto producto = new Producto();

        producto.setIdProducto( product.getProductId() );
        producto.setNombreProducto( product.getName() );
        producto.setCantidadStock( product.getStock() );
        producto.setDescripcion( product.getDescription() );
        producto.setMarca( product.getBrand() );
        producto.setEstilo( product.getStyle() );
        producto.setImagen( product.getImage() );
        producto.setPrecio( product.getPrice() );
        producto.setIdCervecero( product.getBrewerId() );

        return producto;
    }
}

package com.example.ConexionBD_3525;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProducto extends CrudRepository<Producto,Integer> {
    
    @Query(value="SELECT * FROM producto "
            + "WHERE nombre LIKE %?1% "
            + "OR descripcion LIKE %?1% "
            + "OR precio LIKE %?1%",nativeQuery=true)
    List<Producto> buscarPorTodo(String dato);
}

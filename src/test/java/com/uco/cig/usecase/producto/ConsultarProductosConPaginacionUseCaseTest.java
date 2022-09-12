package com.uco.cig.usecase.producto;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.producto.Producto;
import com.uco.cig.domain.producto.ports.ProductoRepository;
import com.uco.cig.generate.ProductoHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ConsultarProductosConPaginacionUseCaseTest {

    ProductoRepository productoRepository;

    ConsultarProductosConPaginacionUseCase consultarProductosConPaginacionUseCase;

    @BeforeEach
    public void setup(){
        productoRepository = mock(ProductoRepository.class);
        consultarProductosConPaginacionUseCase = new ConsultarProductosConPaginacionUseCase(productoRepository);
    }

    @Test
    public void consultarProductosConPaginacionTest() throws BusinessException {
        List<Producto> productos = Arrays.asList(
                ProductoHelper.crearProducto(),
                ProductoHelper.crearProducto()
        );

        Integer page = 1, size = 1;
        String order = "Order";
        boolean asc = true;

        when(productoRepository.findAll(page,size,order,asc)).thenReturn(productos);

        List<Producto> result = consultarProductosConPaginacionUseCase.consultar(page,size,order,asc);

        assertEquals(2, result.size());
        assertEquals(productos, result);
    }

}
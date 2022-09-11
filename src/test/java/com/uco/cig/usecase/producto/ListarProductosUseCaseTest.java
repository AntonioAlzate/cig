package com.uco.cig.usecase.producto;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.producto.Producto;
import com.uco.cig.domain.producto.ports.ProductoRepository;
import com.uco.cig.generate.ProductoHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ListarProductosUseCaseTest {

    ProductoRepository productoRepository;

    ListarProductosUseCase listarProductosUseCase;

    @BeforeEach
    public void setup(){
        productoRepository = mock(ProductoRepository.class);
        listarProductosUseCase = new ListarProductosUseCase(productoRepository);
    }

    @Test
    public void listarProductosUseCaseTest() throws BusinessException {

        List<Producto> productos = Arrays.asList(
                ProductoHelper.crearProducto(),
                ProductoHelper.crearProducto(),
                ProductoHelper.crearProducto(),
                ProductoHelper.crearProducto()
        );

        when(productoRepository.findAll()).thenReturn(productos);

        List<Producto> result = listarProductosUseCase.listar();

        assertEquals(4, result.size());
        assertEquals(productos, result);
    }
}
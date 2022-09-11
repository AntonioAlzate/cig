package com.uco.cig.usecase.producto.categoria;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.categoria.Categoria;
import com.uco.cig.domain.categoria.ports.CategoriaRepository;
import com.uco.cig.generate.CategoriaHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ListarCategoriasUseCaseTest {

    CategoriaRepository categoriaRepository;

    ListarCategoriasUseCase listarCategoriasUseCase;

    @BeforeEach
    public void setup(){
        categoriaRepository = mock(CategoriaRepository.class);
        listarCategoriasUseCase = new ListarCategoriasUseCase(categoriaRepository);
    }

    @Test
    public void listarCategoriasUseCaseTest() throws BusinessException {
        List<Categoria> categorias = Arrays.asList(
                CategoriaHelper.crearCategoria(),
                CategoriaHelper.crearCategoria()
        );

        when(categoriaRepository.findAll()).thenReturn(categorias);

        List<Categoria> result = listarCategoriasUseCase.listar();

        assertEquals(2, result.size());
        assertEquals(categorias, result);
    }
}
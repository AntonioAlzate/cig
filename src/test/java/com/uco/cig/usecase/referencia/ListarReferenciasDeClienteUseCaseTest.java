package com.uco.cig.usecase.referencia;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.referencia.Referencia;
import com.uco.cig.domain.referencia.ports.ReferenciaRepository;
import com.uco.cig.generate.ClienteHelper;
import com.uco.cig.generate.ReferenciasHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Ref;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ListarReferenciasDeClienteUseCaseTest {

    ReferenciaRepository referenciaRepository;

    ListarReferenciasDeClienteUseCase listarReferenciasDeClienteUseCase;

    @BeforeEach
    public void setup(){
        referenciaRepository = mock(ReferenciaRepository.class);
        listarReferenciasDeClienteUseCase = new ListarReferenciasDeClienteUseCase(referenciaRepository);
    }

    @Test
    public void listarReferenciasDeClienteUseCaseTest() throws BusinessException {
        Cliente cliente = ClienteHelper.crearNuevoCliente();
        List<Referencia> referencias = Arrays.asList(
                ReferenciasHelper.crearReferencia(cliente),
                ReferenciasHelper.crearReferencia(cliente),
                ReferenciasHelper.crearReferencia(cliente)
        );

        when(referenciaRepository.findAllByCliente(cliente.getId())).thenReturn(referencias);

        List<Referencia> result = listarReferenciasDeClienteUseCase.listar(cliente.getId());

        assertEquals(3, result.size());
        assertEquals(referencias, result);
        Mockito.verify(referenciaRepository).findAllByCliente(cliente.getId());
    }

}
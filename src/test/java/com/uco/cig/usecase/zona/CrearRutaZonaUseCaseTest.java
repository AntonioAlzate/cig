package com.uco.cig.usecase.zona;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.ciudad.Ciudad;
import com.uco.cig.domain.departamento.Departamento;
import com.uco.cig.domain.pais.Pais;
import com.uco.cig.domain.region.Region;
import com.uco.cig.domain.zona.Zona;
import com.uco.cig.domain.zona.ports.ZonaRepository;
import com.uco.cig.shared.dtos.RutaCreacionDTO;
import com.uco.cig.usecase.ciudad.CrearCiudadUseCase;
import com.uco.cig.usecase.ciudad.ListarCiudadesUseCase;
import com.uco.cig.usecase.departamento.CrearDepartamentoUseCase;
import com.uco.cig.usecase.departamento.ListarDepartamentosUseCase;
import com.uco.cig.usecase.pais.CrearPaisUseCase;
import com.uco.cig.usecase.pais.ListarPaisesUseCase;
import com.uco.cig.usecase.region.CrearRegionUseCase;
import com.uco.cig.usecase.region.ListarRegionesUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;


class CrearRutaZonaUseCaseTest {

    ZonaRepository zonaRepository;
    ListarPaisesUseCase listarPaisesUseCase;
    CrearPaisUseCase crearPaisUseCase;
    ListarDepartamentosUseCase listarDepartamentosUseCase;
    CrearDepartamentoUseCase crearDepartamentoUseCase;
    ListarRegionesUseCase listarRegionesUseCase;
    CrearRegionUseCase crearRegionUseCase;
    ListarCiudadesUseCase listarCiudadesUseCase;
    CrearCiudadUseCase crearCiudadUseCase;
    ListarZonasCiudadUseCase listarZonasCiudadUseCase;

    CrearRutaZonaUseCase crearRutaZonaUseCase;

    @BeforeEach
    void setup() {
        zonaRepository = mock(ZonaRepository.class);
        listarPaisesUseCase = mock(ListarPaisesUseCase.class);
        crearPaisUseCase = mock(CrearPaisUseCase.class);
        listarDepartamentosUseCase = mock(ListarDepartamentosUseCase.class);
        crearDepartamentoUseCase = mock(CrearDepartamentoUseCase.class);
        listarRegionesUseCase = mock(ListarRegionesUseCase.class);
        crearRegionUseCase = mock(CrearRegionUseCase.class);
        listarCiudadesUseCase = mock(ListarCiudadesUseCase.class);
        crearCiudadUseCase = mock(CrearCiudadUseCase.class);
        listarZonasCiudadUseCase = mock(ListarZonasCiudadUseCase.class);

        crearRutaZonaUseCase = new CrearRutaZonaUseCase(
                zonaRepository,
                listarPaisesUseCase,
                crearPaisUseCase,
                listarDepartamentosUseCase,
                crearDepartamentoUseCase,
                listarRegionesUseCase,
                crearRegionUseCase,
                listarCiudadesUseCase,
                crearCiudadUseCase,
                listarZonasCiudadUseCase
        );
    }

    @Test
    void crearZonaTodosCamposInexistentesTest() throws BusinessException {
        RutaCreacionDTO rutaCreacionDTO= crearConExistentes();
        Pais pais = Pais.construir(1, "pais");
        Departamento departamento = Departamento.construir(1, "dep", pais);
        Region region = Region.construir(1, "reg", departamento);
        Ciudad ciudad = Ciudad.construir(1, "ciu", region);
        Zona zona = Zona.construir(1, "zonaExistentes", ciudad);


        when(listarPaisesUseCase.listar()).thenReturn(new ArrayList<>());
        when(listarDepartamentosUseCase.listar(1)).thenReturn(new ArrayList<>());
        when(listarRegionesUseCase.listar(1)).thenReturn(new ArrayList<>());
        when(listarCiudadesUseCase.listar(1)).thenReturn(new ArrayList<>());
        when(listarZonasCiudadUseCase.listar(1)).thenReturn(new ArrayList<>());

        when(crearPaisUseCase.crear("pais")).thenReturn(Pais.construir(1, "pais"));
        when(crearDepartamentoUseCase.crear(any(), any())).thenReturn(departamento);
        when(crearRegionUseCase.crear(any(), any())).thenReturn(region);
        when(crearCiudadUseCase.crear(any(), any())).thenReturn(ciudad);
        when(crearRutaZonaUseCase.crearRuta(rutaCreacionDTO)).thenReturn(zona);


        Zona result = crearRutaZonaUseCase.crearRuta(rutaCreacionDTO);

        assertEquals(zona, result);
    }

    @Test
    void crearZonaTodosCamposExistentesTest() throws BusinessException {
        RutaCreacionDTO rutaCreacionDTO= crearConExistentes();
        List<Pais> paises = Arrays.asList(Pais.construir(1, "pais"));
        List<Departamento> departamentos = Arrays.asList(Departamento.construir(1, "dep", paises.get(0)));
        List<Region> regiones = Arrays.asList(Region.construir(1, "reg", departamentos.get(0)));
        List<Ciudad> ciudades = Arrays.asList(Ciudad.construir(1, "ciu", regiones.get(0)));
        List<Zona> zonas = Arrays.asList(Zona.construir(1, "xxx", ciudades.get(0)));


        when(listarPaisesUseCase.listar()).thenReturn(paises);
        when(listarDepartamentosUseCase.listar(1)).thenReturn(departamentos);
        when(listarRegionesUseCase.listar(1)).thenReturn(regiones);
        when(listarCiudadesUseCase.listar(1)).thenReturn(ciudades);
        when(listarZonasCiudadUseCase.listar(1)).thenReturn(zonas);
        when(zonaRepository.save(any(), any())).thenReturn(zonas.get(0));

        Zona result = crearRutaZonaUseCase.crearRuta(rutaCreacionDTO);

        assertEquals(zonas.get(0), result);
    }

    private RutaCreacionDTO crearConExistentes() {
        return new RutaCreacionDTO(
                1,
                "pais",
                1,
                "dep",
                1,
                "reg",
                1,
                "ciu",
                "zonaExistentes"
        );
    }

}
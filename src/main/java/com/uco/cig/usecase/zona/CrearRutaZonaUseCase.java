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
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrearRutaZonaUseCase {

    private static final String RUTA_YA_EXISTENTE = "Ya existe una ruta para está ciudad con el mismo nombre";

    private final ZonaRepository zonaRepository;
    private final ListarPaisesUseCase listarPaisesUseCase;
    private final CrearPaisUseCase crearPaisUseCase;
    private final ListarDepartamentosUseCase listarDepartamentosUseCase;
    private final CrearDepartamentoUseCase crearDepartamentoUseCase;
    private final ListarRegionesUseCase listarRegionesUseCase;
    private final CrearRegionUseCase crearRegionUseCase;
    private final ListarCiudadesUseCase listarCiudadesUseCase;
    private final CrearCiudadUseCase crearCiudadUseCase;
    private final ListarZonasCiudadUseCase listarZonasCiudadUseCase;

    public CrearRutaZonaUseCase(ZonaRepository zonaRepository, ListarPaisesUseCase listarPaisesUseCase, CrearPaisUseCase crearPaisUseCase, ListarDepartamentosUseCase listarDepartamentosUseCase, CrearDepartamentoUseCase crearDepartamentoUseCase, ListarRegionesUseCase listarRegionesUseCase, CrearRegionUseCase crearRegionUseCase, ListarCiudadesUseCase listarCiudadesUseCase, CrearCiudadUseCase crearCiudadUseCase, ListarZonasCiudadUseCase listarZonasCiudadUseCase) {
        this.zonaRepository = zonaRepository;
        this.listarPaisesUseCase = listarPaisesUseCase;
        this.crearPaisUseCase = crearPaisUseCase;
        this.listarDepartamentosUseCase = listarDepartamentosUseCase;
        this.crearDepartamentoUseCase = crearDepartamentoUseCase;
        this.listarRegionesUseCase = listarRegionesUseCase;
        this.crearRegionUseCase = crearRegionUseCase;
        this.listarCiudadesUseCase = listarCiudadesUseCase;
        this.crearCiudadUseCase = crearCiudadUseCase;
        this.listarZonasCiudadUseCase = listarZonasCiudadUseCase;
    }

    public Zona crearRuta(RutaCreacionDTO creacionDTO) throws BusinessException {

        Pais paisRuta = traerOCrearPais(creacionDTO.getIdPais(), creacionDTO.getNombrePais());

        Departamento departamentoRuta = traerOCrearDepartamento(creacionDTO.getIdDepartamento(), creacionDTO.getNombreDepartamento(), paisRuta);

        Region regionRuta = traerOCrearRegion(creacionDTO.getIdRegion(), creacionDTO.getNombreRegion(), departamentoRuta);

        Ciudad ciudadRuta = traerOCrearCiudad(creacionDTO.getIdCiudad(), creacionDTO.getNombreCiudad(), regionRuta);

        Optional<Zona> zona = listarZonasCiudadUseCase.listar(ciudadRuta.getId()).stream().filter(z -> z.getNombre().equals(creacionDTO.getNombreZona())).findFirst();

        if (zona.isPresent())
            throw new BusinessException(RUTA_YA_EXISTENTE);

        return zonaRepository.save(creacionDTO.getNombreZona().trim().toUpperCase(), ciudadRuta);
    }

    private Ciudad traerOCrearCiudad(Integer idCiudad, String nombreCiudad, Region regionRuta) throws BusinessException {

        Optional<Ciudad> ciudadRuta = listarCiudadesUseCase.listar(regionRuta.getId()).stream().filter(ciudad -> ciudad.getId().equals(idCiudad)).findFirst();

        if (ciudadRuta.isEmpty())
            return crearCiudadUseCase.crear(nombreCiudad.trim().toUpperCase(), regionRuta);

        return ciudadRuta.get();
    }

    private Region traerOCrearRegion(Integer idRegion, String nombreRegion, Departamento departamentoRuta) throws BusinessException {

        Optional<Region> regionRuta = listarRegionesUseCase.listar(departamentoRuta.getId()).stream().filter(region -> region.getId().equals(idRegion)).findFirst();

        if (regionRuta.isEmpty())
            return crearRegionUseCase.crear(nombreRegion.trim().toUpperCase(), departamentoRuta);

        return regionRuta.get();
    }

    private Departamento traerOCrearDepartamento(Integer idDepartamento, String nombreDepartamento, Pais pais) throws BusinessException {
        Optional<Departamento> departamentoRuta = listarDepartamentosUseCase.listar(pais.getId()).stream().filter(departamento -> departamento.getId().equals(idDepartamento)).findFirst();

        if (departamentoRuta.isEmpty())
            return crearDepartamentoUseCase.crear(nombreDepartamento.trim().toUpperCase(), pais);

        return departamentoRuta.get();
    }

    private Pais traerOCrearPais(Integer idPais, String nombrePais) throws BusinessException {
        Optional<Pais> paisRuta = listarPaisesUseCase.listar().stream().filter(pais -> pais.getId().equals(idPais)).findFirst();

        if (paisRuta.isEmpty())
            return crearPaisUseCase.crear(nombrePais.trim().toUpperCase());

        return paisRuta.get();
    }
}

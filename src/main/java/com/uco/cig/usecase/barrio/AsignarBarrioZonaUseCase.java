package com.uco.cig.usecase.barrio;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.barrio.ports.BarrioRepository;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.ciudad.Ciudad;
import com.uco.cig.domain.departamento.Departamento;
import com.uco.cig.domain.pais.Pais;
import com.uco.cig.domain.region.Region;
import com.uco.cig.domain.zona.Zona;
import com.uco.cig.shared.dtos.AsignarBarrioZonaDTO;
import com.uco.cig.usecase.ciudad.ListarCiudadesUseCase;
import com.uco.cig.usecase.departamento.ListarDepartamentosUseCase;
import com.uco.cig.usecase.pais.ListarPaisesUseCase;
import com.uco.cig.usecase.region.ListarRegionesUseCase;
import com.uco.cig.usecase.zona.ListarZonasCiudadUseCase;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AsignarBarrioZonaUseCase {
    private final ListarPaisesUseCase listarPaisesUseCase;
    private final ListarDepartamentosUseCase listarDepartamentosUseCase;
    private final ListarRegionesUseCase listarRegionesUseCase;
    private final ListarCiudadesUseCase listarCiudadesUseCase;
    private final ListarZonasCiudadUseCase listarZonasCiudadUseCase;
    private final BarrioRepository barrioRepository;

    public AsignarBarrioZonaUseCase(ListarPaisesUseCase listarPaisesUseCase, ListarDepartamentosUseCase listarDepartamentosUseCase, ListarRegionesUseCase listarRegionesUseCase, ListarCiudadesUseCase listarCiudadesUseCase, ListarZonasCiudadUseCase listarZonasCiudadUseCase, BarrioRepository barrioRepository) {
        this.listarPaisesUseCase = listarPaisesUseCase;
        this.listarDepartamentosUseCase = listarDepartamentosUseCase;
        this.listarRegionesUseCase = listarRegionesUseCase;
        this.listarCiudadesUseCase = listarCiudadesUseCase;
        this.listarZonasCiudadUseCase = listarZonasCiudadUseCase;
        this.barrioRepository = barrioRepository;
    }

    public Barrio asignarZona(AsignarBarrioZonaDTO asignarBarrioZonaDTO) throws BusinessException {
        Pais paisRuta = traerOCrearPais(asignarBarrioZonaDTO.getIdPais());

        Departamento departamentoRuta = traerOCrearDepartamento(asignarBarrioZonaDTO.getIdDepartamento(), paisRuta);

        Region regionRuta = traerOCrearRegion(asignarBarrioZonaDTO.getIdRegion(), departamentoRuta);

        Ciudad ciudadRuta = traerOCrearCiudad(asignarBarrioZonaDTO.getIdCiudad(), regionRuta);

        Zona zonaRuta = traerZona(asignarBarrioZonaDTO.getIdZona(), ciudadRuta);

        return barrioRepository.assignZone(zonaRuta.getId(), asignarBarrioZonaDTO.getIdBarrio(), asignarBarrioZonaDTO.getNombreBarrio());

    }

    private Zona traerZona(Integer idZona, Ciudad ciudadRuta) {
        Optional<Zona> zonaRuta = listarZonasCiudadUseCase.listar(ciudadRuta.getId()).stream().filter(zona -> zona.getId().equals(idZona)).findFirst();

        if (zonaRuta.isEmpty())
            throw new NotFoundException("Zona no encontrada");

        return zonaRuta.get();
    }

    private Ciudad traerOCrearCiudad(Integer idCiudad, Region regionRuta) throws BusinessException {

        Optional<Ciudad> ciudadRuta = listarCiudadesUseCase.listar(regionRuta.getId()).stream().filter(ciudad -> ciudad.getId().equals(idCiudad)).findFirst();

        if (ciudadRuta.isEmpty())
            throw new NotFoundException("Ruta no encontrada");

        return ciudadRuta.get();
    }

    private Region traerOCrearRegion(Integer idRegion, Departamento departamentoRuta) throws BusinessException {

        Optional<Region> regionRuta = listarRegionesUseCase.listar(departamentoRuta.getId()).stream().filter(region -> region.getId().equals(idRegion)).findFirst();

        if (regionRuta.isEmpty())
            throw new NotFoundException("Región no encontrada");

        return regionRuta.get();
    }

    private Departamento traerOCrearDepartamento(Integer idDepartamento, Pais pais) throws BusinessException {
        Optional<Departamento> departamentoRuta = listarDepartamentosUseCase.listar(pais.getId()).stream().filter(departamento -> departamento.getId().equals(idDepartamento)).findFirst();

        if (departamentoRuta.isEmpty())
            throw new NotFoundException("Departamento no encontrada");

        return departamentoRuta.get();
    }

    private Pais traerOCrearPais(Integer idPais) throws BusinessException {
        Optional<Pais> paisRuta = listarPaisesUseCase.listar().stream().filter(pais -> pais.getId().equals(idPais)).findFirst();

        if (paisRuta.isEmpty())
            throw new NotFoundException("Pais no encontrada");

        return paisRuta.get();
    }
}

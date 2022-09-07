package com.uco.cig.usecase.parentesco;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.parentesco.Parentesco;
import com.uco.cig.domain.parentesco.ports.ParentescoRespository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ObtenerParentescoPorIdUseCase {

    private static final String PARENTESCO_CON_ID_NO_ENCONTRADO = "El parentesco con id especificado no ha sido encontrado";
    private final ParentescoRespository parentescoRespository;

    public ObtenerParentescoPorIdUseCase(ParentescoRespository parentescoRespository) {
        this.parentescoRespository = parentescoRespository;
    }

    public Parentesco obtener(Integer id) {
        Optional<Parentesco> parentesco = parentescoRespository.findById(id);

        if(parentesco.isEmpty()){
            throw new NotFoundException(PARENTESCO_CON_ID_NO_ENCONTRADO);
        }

        return parentesco.get();
    }
}

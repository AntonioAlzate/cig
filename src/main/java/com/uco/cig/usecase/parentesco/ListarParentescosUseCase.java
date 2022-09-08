package com.uco.cig.usecase.parentesco;

import com.uco.cig.domain.parentesco.Parentesco;
import com.uco.cig.domain.parentesco.ports.ParentescoRespository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ListarParentescosUseCase {


    private final ParentescoRespository parentescoRespository;

    public ListarParentescosUseCase(ParentescoRespository parentescoRespository) {
        this.parentescoRespository = parentescoRespository;
    }

    public List<Parentesco> listar(){
        return parentescoRespository.findAll();
    }
}

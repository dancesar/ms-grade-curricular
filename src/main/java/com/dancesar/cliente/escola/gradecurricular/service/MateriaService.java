package com.dancesar.cliente.escola.gradecurricular.service;

import com.dancesar.cliente.escola.gradecurricular.dto.MateriaDto;
import com.dancesar.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.dancesar.cliente.escola.gradecurricular.exception.MateriaException;
import com.dancesar.cliente.escola.gradecurricular.repository.IMateriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MateriaService implements  IMateriaService{

    @Autowired
    private IMateriaRepository iMateriaRepository;

    @Override
    public Boolean cadastrarMateria(MateriaDto materiaDto) {
        try {
            ModelMapper mapper = new ModelMapper();
            MateriaEntity materiaEntity = mapper.map(materiaDto, MateriaEntity.class);
            this.iMateriaRepository.save(materiaEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<MateriaEntity> Listar() {
        try {
            return this.iMateriaRepository.findAll();
        } catch (Exception e){
            return new ArrayList<>();
        }
    }

    @Override
    public MateriaEntity consultar(Long id) {
        try {
            Optional<MateriaEntity> materiaOptional = this.iMateriaRepository.findById(id);
            if (materiaOptional.isPresent()) {
                return materiaOptional.get();
            }
            throw new MateriaException("Matéria não encontrada.", HttpStatus.NOT_FOUND);
        } catch (MateriaException m) {
            throw m;
        } catch (Exception e) {
            throw new MateriaException("Erro interno identificado. Contate o suporte.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean atualizar(MateriaDto materiaDto) {
        try {
            ModelMapper mapper = new ModelMapper();
            this.consultar(materiaDto.getId());

            MateriaEntity materiaAtualizada = mapper.map(materiaDto, MateriaEntity.class);

            this.iMateriaRepository.save(materiaAtualizada);

            return Boolean.TRUE;

        } catch (MateriaException m) {
            throw m;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean excluir(Long id) {
        try {
            this.consultar(id);
            this.iMateriaRepository.deleteById(id);
            return true;
        } catch (MateriaException m) {
            throw m;
        } catch (Exception e){
            throw e;
        }
    }
}
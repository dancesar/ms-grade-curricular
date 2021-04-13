package com.dancesar.cliente.escola.gradecurricular.service;

import com.dancesar.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.dancesar.cliente.escola.gradecurricular.exception.MateriaException;
import com.dancesar.cliente.escola.gradecurricular.repository.IMateriaRepository;
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
    public Boolean cadastrarMateria(MateriaEntity materiaEntity) {
        try {
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
    public Boolean atualizar(MateriaEntity materiaEntity) {
        try {
            Optional<MateriaEntity> materiaOptional = this.iMateriaRepository.findById(materiaEntity.getId());

            if(materiaOptional.isPresent()) {
                MateriaEntity materiaAtualizada = materiaOptional.get();

                materiaAtualizada.setNome(materiaEntity.getNome());
                materiaAtualizada.setHoras(materiaEntity.getHoras());
                materiaAtualizada.setCodigo(materiaEntity.getCodigo());
                materiaAtualizada.setFrequencia(materiaEntity.getFrequencia());

                this.iMateriaRepository.save(materiaAtualizada);

                return true;
            }
        return false;
        } catch (Exception e) {
            return false;
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
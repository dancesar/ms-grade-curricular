package com.dancesar.cliente.escola.gradecurricular.service;

import com.dancesar.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.dancesar.cliente.escola.gradecurricular.repository.IMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public Boolean atualizar(MateriaEntity materiaEntity) {
        try {
            MateriaEntity materiaAtualizada = this.iMateriaRepository.findById(materiaEntity.getId()).get();

            materiaAtualizada.setNome(materiaEntity.getNome());
            materiaAtualizada.setHoras(materiaEntity.getHoras());
            materiaAtualizada.setCodigo(materiaEntity.getCodigo());
            materiaAtualizada.setFrequencia(materiaEntity.getFrequencia());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean excluir(Long id) {
        try {
            this.iMateriaRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
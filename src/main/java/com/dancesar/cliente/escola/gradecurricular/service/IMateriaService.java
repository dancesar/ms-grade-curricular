package com.dancesar.cliente.escola.gradecurricular.service;

import com.dancesar.cliente.escola.gradecurricular.entity.MateriaEntity;

import java.util.List;

public interface IMateriaService {

    public Boolean cadastrarMateria(final MateriaEntity materiaEntity);

    public List<MateriaEntity> Listar();

    public Boolean atualizar(final MateriaEntity materiaEntity);

    public Boolean excluir(final Long id);
}
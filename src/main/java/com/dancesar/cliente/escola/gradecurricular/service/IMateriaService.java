package com.dancesar.cliente.escola.gradecurricular.service;

import com.dancesar.cliente.escola.gradecurricular.dto.MateriaDto;
import com.dancesar.cliente.escola.gradecurricular.entity.MateriaEntity;

import java.util.List;

public interface IMateriaService {

    public Boolean cadastrarMateria(final MateriaDto materiaDto);

    public List<MateriaEntity> Listar();

    public MateriaEntity consultar(final Long id);

    public Boolean atualizar(final MateriaDto materiaDto);

    public Boolean excluir(final Long id);
}
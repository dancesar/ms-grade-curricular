package com.dancesar.cliente.escola.gradecurricular.service;

import com.dancesar.cliente.escola.gradecurricular.dto.MateriaDto;

import java.util.List;

public interface IMateriaService {

    public Boolean cadastrarMateria(final MateriaDto materiaDto);

    public List<MateriaDto> Listar();

    public MateriaDto consultar(final Long id);

    public List<MateriaDto> listarPorHorarioMinimo(int horaMinima);

    public Boolean atualizar(final MateriaDto materiaDto);

    public Boolean excluir(final Long id);
}
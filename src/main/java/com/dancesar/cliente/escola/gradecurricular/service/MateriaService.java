package com.dancesar.cliente.escola.gradecurricular.service;

import com.dancesar.cliente.escola.gradecurricular.dto.MateriaDto;
import com.dancesar.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.dancesar.cliente.escola.gradecurricular.exception.MateriaException;
import com.dancesar.cliente.escola.gradecurricular.repository.IMateriaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = {"materia"})
@Service
public class MateriaService implements  IMateriaService{

    private static final String MENSAGEM_ERRO = "Erro interno identificado. Contate o suporte.";
    private static final String MENSAGEM_NAO_ENCONTRADA = "Matéria não encontrada.";
    private IMateriaRepository iMateriaRepository;
    private ModelMapper mapper;

    @Autowired
    public MateriaService(IMateriaRepository iMateriaRepository){
        this.mapper = new ModelMapper();
        this.iMateriaRepository = iMateriaRepository;
    }

    @CacheEvict(allEntries = true)
    @Override
    public Boolean cadastrarMateria(MateriaDto materiaDto) {
        try {
            MateriaEntity materiaEntity = this.mapper.map(materiaDto, MateriaEntity.class);
            this.iMateriaRepository.save(materiaEntity);
            return Boolean.TRUE;
        } catch (Exception e) {
            throw new MateriaException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CachePut(unless = "#result.size()<3")
    @Override
    public List<MateriaDto> Listar() {
        try {
            return this.mapper.map(this.iMateriaRepository.findAll(), new TypeToken<List<MateriaDto>>() {}.getType());
        } catch (Exception e){
            throw new MateriaException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CachePut(key = "#id")
    @Override
    public MateriaDto consultar(Long id) {
        try {
            Optional<MateriaEntity> materiaOptional = this.iMateriaRepository.findById(id);
            if (materiaOptional.isPresent()) {
                return this.mapper.map(materiaOptional.get(), MateriaDto.class);
            }
            throw new MateriaException(MENSAGEM_NAO_ENCONTRADA, HttpStatus.NOT_FOUND);
        } catch (MateriaException m) {
            throw m;
        } catch (Exception e) {
            throw new MateriaException(MENSAGEM_ERRO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Caching(evict = {
            @CacheEvict(key = "#materia.id"),
            @CacheEvict(value = "escola", key = "#escola.id")})
    @Override
    public Boolean atualizar(MateriaDto materiaDto) {
        try {
            this.consultar(materiaDto.getId());
            MateriaEntity materiaAtualizada = this.mapper.map(materiaDto, MateriaEntity.class);

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
            return Boolean.TRUE;
        } catch (MateriaException m) {
            throw m;
        } catch (Exception e){
            throw e;
        }
    }
}
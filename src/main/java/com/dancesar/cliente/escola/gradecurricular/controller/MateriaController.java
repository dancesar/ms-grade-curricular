package com.dancesar.cliente.escola.gradecurricular.controller;

import com.dancesar.cliente.escola.gradecurricular.dto.MateriaDto;
import com.dancesar.cliente.escola.gradecurricular.model.Response;
import com.dancesar.cliente.escola.gradecurricular.service.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private IMateriaService iMateriaService;

    @GetMapping
    public ResponseEntity<List<MateriaDto>> listaMaterias(){
        return ResponseEntity.status(HttpStatus.OK).body(this.iMateriaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<MateriaDto>> consultaMateria(@PathVariable Long id){
        Response<MateriaDto> response = new Response<>();
        response.setData(this.iMateriaService.consultar(id));
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder
                            .linkTo(WebMvcLinkBuilder
                            .methodOn(MateriaController.class)
                            .consultaMateria(id))
                            .withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("horario-minimo/{horaMinima}")
    public ResponseEntity<Response<List<MateriaDto>>> consultaMateriaPorHoraMinima(@PathVariable Integer horaMinima){
        Response<List<MateriaDto>> response = new Response<>();
        List<MateriaDto> materia = this.iMateriaService.listarPorHorarioMinimo(horaMinima);
        response.setData(materia);
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder
                                .linkTo(WebMvcLinkBuilder
                                .methodOn(MateriaController.class)
                                .consultaMateriaPorHoraMinima(horaMinima))
                                .withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Boolean> cadastrarMateria(@Valid @RequestBody MateriaDto materiaDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.iMateriaService.cadastrarMateria(materiaDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<Boolean> atualizaMateria(@Valid @RequestBody MateriaDto materiaDto){
        return ResponseEntity.status(HttpStatus.OK).body(this.iMateriaService.atualizar(materiaDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deletaMateria(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.iMateriaService.excluir(id));
    }
}
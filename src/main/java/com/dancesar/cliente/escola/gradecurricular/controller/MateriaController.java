package com.dancesar.cliente.escola.gradecurricular.controller;

import com.dancesar.cliente.escola.gradecurricular.dto.MateriaDto;
import com.dancesar.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.dancesar.cliente.escola.gradecurricular.repository.IMateriaRepository;
import com.dancesar.cliente.escola.gradecurricular.service.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private IMateriaRepository materiaRepository;

    @Autowired
    private IMateriaService iMateriaService;

    @GetMapping
    public ResponseEntity<List<MateriaDto>> listaMaterias(){
        return ResponseEntity.status(HttpStatus.OK).body(this.iMateriaService.Listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDto> consultaMateria(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.iMateriaService.consultar(id));
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
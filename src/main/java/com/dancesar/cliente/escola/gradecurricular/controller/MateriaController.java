package com.dancesar.cliente.escola.gradecurricular.controller;

import com.dancesar.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.dancesar.cliente.escola.gradecurricular.repository.IMateriaRepository;
import com.dancesar.cliente.escola.gradecurricular.service.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private IMateriaRepository materiaRepository;

    @Autowired
    private IMateriaService iMateriaService;

    @GetMapping
    public ResponseEntity<List<MateriaEntity>> listaMaterias(){
        return ResponseEntity.status(HttpStatus.OK).body(this.iMateriaService.Listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaEntity> consultaMateria(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.iMateriaService.consultar(id));
    }

    @PostMapping
    public ResponseEntity<Boolean> cadastrarMateria(@RequestBody MateriaEntity materiaEntity){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.iMateriaService.cadastrarMateria(materiaEntity));
    }

    @PutMapping("{id}")
    public ResponseEntity<Boolean> atualizaMateria(@RequestBody MateriaEntity materiaEntity){
        return ResponseEntity.status(HttpStatus.OK).body(this.iMateriaService.atualizar(materiaEntity));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deletaMateria(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.iMateriaService.excluir(id));
    }
}
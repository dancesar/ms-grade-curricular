package com.dancesar.cliente.escola.gradecurricular.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.dancesar.cliente.escola.gradecurricular.dto.MateriaDto;
import com.dancesar.cliente.escola.gradecurricular.model.Response;
import com.dancesar.cliente.escola.gradecurricular.service.IMateriaService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(JUnitPlatform.class)
public class MateriaControllerUnitTest {

    @LocalServerPort
    private Integer port;

    @MockBean
    private IMateriaService materiaService;

    @Autowired
    private TestRestTemplate restTemplate;

    private static MateriaDto materiaDto;

    @BeforeAll
    public static void init(){
        materiaDto = new MateriaDto();

        materiaDto.setId(1L);
        materiaDto.setCodigo("ILP001");
        materiaDto.setFrequencia(1);
        materiaDto.setHoras(64);
        materiaDto.setNome("INTRODUÇÃO A LINGUAGEM DE PROGRAMAÇÃO - I");
    }

    @Test
    public void testListarMaterias(){
        Mockito.when(this.materiaService.listar()).thenReturn(new ArrayList<MateriaDto>());

        ResponseEntity<Response<List<MateriaDto>>> materias = restTemplate.exchange("http://127.0.0.1:" + this.port + "/materia/", HttpMethod.GET, null,
                                                                                    new ParameterizedTypeReference<Response<List<MateriaDto>>>() {});
        assertNotNull(materias.getBody().getData());
        assertEquals(200, materias.getBody().getStatusCode());
    }

    @Test
    public void testConsultarMaterias(){
        Mockito.when(this.materiaService.consultar(1L)).thenReturn(materiaDto);

        ResponseEntity<Response<List<MateriaDto>>> materias = restTemplate.exchange("http://http://127.0.0.1:" + this.port + "/materia/", HttpMethod.GET, null,
                new ParameterizedTypeReference<Response<List<MateriaDto>>>() {});
        assertNotNull(materias.getBody().getData());
        assertEquals(200, materias.getBody().getStatusCode());
    }

    @Test
    public void testCadastrarMaterias(){
        Mockito.when(this.materiaService.listar()).thenReturn(new ArrayList<MateriaDto>());

        HttpEntity<MateriaDto> request = new HttpEntity<>(materiaDto);

        ResponseEntity<Response<Boolean>> materias = restTemplate.exchange("http://192.168.0.8:" + this.port + "/materia/", HttpMethod.POST, null,
                new ParameterizedTypeReference<Response<Boolean>>() {});
        assertNotNull(materias.getBody().getData());
        assertEquals(200, materias.getBody().getStatusCode());
    }

    @Test
    public void testAtualizarMaterias(){
        Mockito.when(this.materiaService.atualizar(materiaDto)).thenReturn(Boolean.TRUE);

        HttpEntity<MateriaDto> request = new HttpEntity<>(materiaDto);

        ResponseEntity<Response<Boolean>> materias = restTemplate.exchange("http://http://127.0.0.1:" + this.port + "/materia/", HttpMethod.PUT, null,
                new ParameterizedTypeReference<Response<Boolean>>() {});
        assertNotNull(materias.getBody().getData());
        assertEquals(200, materias.getBody().getStatusCode());
    }

    @Test
    public void testExcluirMaterias(){
        Mockito.when(this.materiaService.excluir(1L)).thenReturn(Boolean.TRUE);

        ResponseEntity<Response<Boolean>> materias = restTemplate.exchange("http://http://127.0.0.1:" + this.port + "/materia/", HttpMethod.DELETE, null,
                new ParameterizedTypeReference<Response<Boolean>>() {});
        assertNotNull(materias.getBody().getData());
        assertEquals(200, materias.getBody().getStatusCode());
    }

    @Test
    public void testConsultarPorHoraMinima(){
        Mockito.when(this.materiaService.listarPorHorarioMinimo(64)).thenReturn(new ArrayList<MateriaDto>());

        ResponseEntity<Response<List<MateriaDto>>> materias = restTemplate.exchange("http://http://127.0.0.1:" + this.port + "/materia/horario-minimo/64", HttpMethod.GET, null,
                new ParameterizedTypeReference<Response<List<MateriaDto>>>() {});
        assertNotNull(materias.getBody().getData());
        assertEquals(200, materias.getBody().getStatusCode());
    }
}
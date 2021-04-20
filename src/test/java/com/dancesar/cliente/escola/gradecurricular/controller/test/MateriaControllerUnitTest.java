package com.dancesar.cliente.escola.gradecurricular.controller.test;

import com.dancesar.cliente.escola.gradecurricular.dto.MateriaDto;
import com.dancesar.cliente.escola.gradecurricular.model.Response;
import com.dancesar.cliente.escola.gradecurricular.service.IMateriaService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MateriaControllerUnitTest {

    @LocalServerPort
    private Integer port;

    @MockBean
    private IMateriaService materiaService;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    public static void init(){}

    @Test
    public void testListarMaterias(){
        Mockito.when(this.materiaService.listar()).thenReturn(new ArrayList<MateriaDto>());

        ResponseEntity<Response<List<MateriaDto>>> materias = restTemplate.exchange("http://192.168.0.8:" + this.port + "/materia/", HttpMethod.GET, null,
                                                                                    new ParameterizedTypeReference<Response<List<MateriaDto>>>() {});
        assertNotNull(materias.getBody().getData());
        assertEquals(200, materias.getBody().getStatusCode());
    }
}
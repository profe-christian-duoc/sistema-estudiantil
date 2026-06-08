package cl.duoc.sistema.controller;

import cl.duoc.sistema.model.Estudiante;
import cl.duoc.sistema.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // 1. Obtener todos los estudiantes
    @GetMapping
    public ResponseEntity<List<Estudiante>> listarTodos() {
        List<Estudiante> estudiantes = estudianteService.listarTodos();
        return ResponseEntity.ok(estudiantes);
    }

    // 2. Obtener un estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> buscarPorId(@PathVariable Long id) {
        Estudiante estudiante = estudianteService.buscarPorId(id);
        return ResponseEntity.ok(estudiante);
    }

    // 3. Crear un nuevo estudiante
    @PostMapping
    public ResponseEntity<Estudiante> crear(@RequestBody Estudiante estudiante) {
        Estudiante nuevoEstudiante = estudianteService.guardar(estudiante);
        return new ResponseEntity<>(nuevoEstudiante, HttpStatus.CREATED);
    }

    // 4. Eliminar un estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        estudianteService.buscarPorId(id);
        estudianteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // 5. Obtener el promedio de un estudiante específico
    @GetMapping("/{id}/promedio")
    public ResponseEntity<Double> obtenerPromedio(@PathVariable Long id) {
        Estudiante estudiante = estudianteService.buscarPorId(id);
        double promedio = estudianteService.calcularPromedio(estudiante);
        return ResponseEntity.ok(promedio);
    }
}

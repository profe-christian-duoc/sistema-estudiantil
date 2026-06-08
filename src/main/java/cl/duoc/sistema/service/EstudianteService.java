package cl.duoc.sistema.service;

import cl.duoc.sistema.NotFoundException;
import cl.duoc.sistema.model.Estudiante;
import cl.duoc.sistema.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public List<Estudiante> listarTodos() {
        return estudianteRepository.findAll();
    }

    // Guardar o actualizar un estudiante
    public Estudiante guardar(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    // Buscar un estudiante por su ID
    public Estudiante buscarPorId(Long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Estudiante no encontrado"));
    }

    // Eliminar un estudiante por su ID
    public void eliminar(Long id) {
        estudianteRepository.deleteById(id);
    }

    // Ejemplo de lógica de negocio: Calcular el promedio de un estudiante
    public double calcularPromedio(Estudiante estudiante) {
        return (estudiante.getNota1() + estudiante.getNota2() + estudiante.getNota3()) / 3.0;
    }
}

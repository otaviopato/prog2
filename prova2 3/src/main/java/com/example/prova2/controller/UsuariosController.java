package com.example.prova2.controller;

import com.example.prova2.model.UsuariosModel;
import com.example.prova2.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UsuariosController {

    @Autowired
    private final UsuariosRepository usuariosRepository;

    public UsuariosController(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }
    @GetMapping("/{id}")
    public UsuariosModel usuariosModel(@PathVariable("id") Long id){
        Optional<UsuariosModel> usuariosFind = this.usuariosRepository.findById(id);
        if (usuariosFind.isPresent()) {
            UsuariosModel usuariosModel = usuariosFind.get();
            return usuariosModel;}
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
    }

    @PostMapping("/")
    public UsuariosModel usuariosModel(@RequestBody UsuariosModel usuariosModel){
        return this.usuariosRepository.save(usuariosModel);
    }

    @GetMapping("/list")
    public List<UsuariosModel> list(@RequestHeader(value = "Authorization", required = false) String authKey) {
        return this.usuariosRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteEmployee(@PathVariable("id") Long id,
                                               @RequestHeader(value = "Authorization", required = false) String authKey) {
        Optional<UsuariosModel> existsUsuarios = this.usuariosRepository.findById(id);
        if (existsUsuarios.isPresent()) {
            this.usuariosRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
    }

    @PutMapping("/{id}")
    public UsuariosModel putUpdateUsuariosById(@PathVariable("id") Long id,
                                         @RequestBody UsuariosModel fieldsToUpdate) {
        Optional<UsuariosModel> usuariosFind = this.usuariosRepository.findById(id);
        if (usuariosFind.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        }
        fieldsToUpdate.setId(id);
        return this.usuariosRepository.save(fieldsToUpdate);
    }

    @PatchMapping("/{id}")
    public UsuariosModel patchUpdateUsuariosById(@PathVariable("id") Long id,
                                          @RequestBody UsuariosModel fieldsToUpdate) {
        Optional<UsuariosModel> usuariosFind = this.usuariosRepository.findById(id);
        if (usuariosFind.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
        }
        UsuariosModel usuariosModel = usuariosFind.get();
        fieldsToUpdate.setId(id);
        // Ugly code area
        fieldsToUpdate.setName(fieldsToUpdate.getName() == null ? usuariosModel.getName() : fieldsToUpdate.getName());
        fieldsToUpdate.setEmail(fieldsToUpdate.getEmail() == null ? usuariosModel.getEmail() : fieldsToUpdate.getEmail());
        fieldsToUpdate.setCpf(fieldsToUpdate.getCpf() == null ? usuariosModel.getCpf() : fieldsToUpdate.getCpf());
        // Ugly code area
        return this.usuariosRepository.save(fieldsToUpdate);
    }

}

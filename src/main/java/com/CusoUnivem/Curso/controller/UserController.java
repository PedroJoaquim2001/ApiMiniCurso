package com.CusoUnivem.Curso.controller;

import com.CusoUnivem.Curso.dto.UserDto;
import com.CusoUnivem.Curso.model.UserModel;
import com.CusoUnivem.Curso.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDto userDto){
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }


    @GetMapping
    public ResponseEntity<Page<UserModel>> getAllProduct(@PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable UUID id){
        Optional<UserModel> userModelOptional = userService.findById(id);

        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id) {
        Optional<UserModel> userModelOptional = userService.findById(id);

        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário para deletar não encontrado.");
        }

        userService.delete(userModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso.");
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid UserDto userDto){
        Optional<UserModel> userModelOptional = userService.findById(id);

        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado para modificação.");
        }

        var userModel = new UserModel();
        userModel = userDto.convert();
        userModel.setUserId(userModelOptional.get().getUserId());

        return ResponseEntity.status(HttpStatus.OK).body(userService.save(userModel));
    }
}

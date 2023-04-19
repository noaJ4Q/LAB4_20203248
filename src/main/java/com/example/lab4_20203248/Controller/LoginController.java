package com.example.lab4_20203248.Controller;

import com.example.lab4_20203248.Entity.User;
import com.example.lab4_20203248.Entity.Vuelo;
import com.example.lab4_20203248.Repository.UserRepository;
import com.example.lab4_20203248.Repository.VueloRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {

    final UserRepository userRepository;
    final VueloRepository vueloRepository;

    public LoginController(UserRepository userRepository, VueloRepository vueloRepository){
        this.userRepository = userRepository;
        this.vueloRepository = vueloRepository;
    }
    @GetMapping(value = {"", "/", "index"})
    public String login(){
        return "inicio";
    }

    @PostMapping("/login/inicioSesion")
    public String validacion(@RequestParam(name = "correo") String correo,
                             @RequestParam(name = "contrasena") String contrasena,
                             Model model){

        Integer id = userRepository.obtenerCredenciales(correo, contrasena);
        if (id != null){
            Optional<User> optionalUser = userRepository.findById(id);
            List<Vuelo> vuelos = vueloRepository.findAll();
            if (optionalUser.isPresent()){
                User user = optionalUser.get();
                model.addAttribute("user", user);
                model.addAttribute("vuelos", vuelos);
                return "homePage";
            }
        }
        return "redirect:index";
    }
}

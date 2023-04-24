package com.example.lab4_20203248.Controller;

import com.example.lab4_20203248.Entity.Reserva;
import com.example.lab4_20203248.Entity.User;
import com.example.lab4_20203248.Entity.Vuelo;
import com.example.lab4_20203248.Repository.ReservaRepository;
import com.example.lab4_20203248.Repository.UserRepository;
import com.example.lab4_20203248.Repository.VueloRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    final UserRepository userRepository;
    final VueloRepository vueloRepository;
    final ReservaRepository reservaRepository;

    public HomeController(UserRepository userRepository, VueloRepository vueloRepository, ReservaRepository reservaRepository){
        this.userRepository = userRepository;
        this.vueloRepository = vueloRepository;
        this.reservaRepository = reservaRepository;
    }
    @GetMapping(value = {"", "/", "index"})
    public String login(){
        return "inicio";
    }

    @PostMapping("/login/inicioSesion")
    public String validacion(@RequestParam(name = "correo") String correo,
                             @RequestParam(name = "contrasena") String contrasena,
                             RedirectAttributes attr){

        Integer id = userRepository.obtenerCredenciales(correo, contrasena);
        if (id != null){
            Optional<User> optionalUser = userRepository.findById(id);

            if (optionalUser.isPresent()){
                User user = optionalUser.get();
                attr.addFlashAttribute("iduser", user.getIduser());
                return "redirect:/inicio";
            }
        }
        return "redirect:/";
    }

    @GetMapping("/inicio")
    public String inicio(Model model){
        List<Vuelo> vuelos = vueloRepository.findAll();
        model.addAttribute("vuelos", vuelos);
        return "homePage";
    }

    @PostMapping("/reservar")
    public String reservar(@RequestParam(name = "precio_total") Float precio_total,
                           @RequestParam(name = "user_iduser") int user_userid,
                           @RequestParam(name = "vuelo_idvuelo") int vuelo_idvuelo,
                           RedirectAttributes attr){
        Reserva reserva = new Reserva();

        Date fecha = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        reserva.setFecha_reserva(formatter.format(fecha));
        reserva.setPrecio_total(precio_total);
        reserva.setEstado_pago("procesado");

        User user = new User();
        user.setIduser(user_userid);
        Vuelo vuelo = new Vuelo();
        vuelo.setIdvuelo(vuelo_idvuelo);
        reserva.setUser(user);
        reserva.setVuelo(vuelo);

        reservaRepository.save(reserva);
        attr.addFlashAttribute("iduser", user.getIduser());
        attr.addFlashAttribute("msg", "Se realizó con exito su reserva");
        return "redirect:/inicio";
    }
}

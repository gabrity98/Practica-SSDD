package es.ssdd.PracticaSSDD.controllers;

import es.ssdd.PracticaSSDD.entities.Usuario;
import es.ssdd.PracticaSSDD.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String inicioUsuario(Model model){
        model.addAttribute("usuario", new Usuario());
        return "inicio-usuario";
    }

    @PostMapping("/")
    public String logInUser(Usuario usuario, HttpSession session){
        Usuario user = usuarioService.comprobarUsuario(usuario.getNombre(), usuario.getEmail());
        if(user!= null){
            session.setAttribute("userID", user.getId());
            return "redirect:/peliculas";
        }
        return "redirect:/";
    }

    @GetMapping("/usuario/agregar")
    public String mostrarFormularioUsuarioAgregar(Model model){
        model.addAttribute("usuario", new Usuario());
        return "agregar-usuario";
    }

    @PostMapping("/usuario/agregar")
    public String agregarUsuario(Usuario usuario){
        usuarioService.crearUsuario(usuario);
        return "redirect:/";
    }

    @GetMapping("/usuarios")
    public String mostrarUsuarios(Model model){
        model.addAttribute("usuarios", usuarioService.getAllUsuarios());
        return "usuarios";
    }

    @GetMapping("/usuario/editar/{id}")
    public String mostrarFormularioUsuarioEditar(@PathVariable Long id, Model model){
        Usuario usuario = usuarioService.getUsuario(id);
        if (usuario == null)
            return "redirect:/";
        model.addAttribute("usuario",usuario);
        return "editar-usuario";
    }

    @PostMapping("/usuario/editar")
    public String editarUsuario(Usuario usuario){
        usuarioService.actualizarUsuario(usuario.getId(), usuario);
        return "redirect:/usuario/detalles/"+usuario.getId();
    }

    @GetMapping("/usuario/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id){
        usuarioService.eliminarUsuario(id);
        return "redirect:/";
    }

    @GetMapping("/usuario/detalles/{id}")
    public String mostrarDetallesUsuario(@PathVariable Long id, Model model){
        Usuario usuario = usuarioService.getUsuario(id);
        if (usuario == null) {
            return "redirect:/";
        }
        model.addAttribute("usuario", usuario);
        return "detalles-usuario";
    }
}

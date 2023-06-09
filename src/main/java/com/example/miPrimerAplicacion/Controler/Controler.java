package com.example.miPrimerAplicacion.Controler;

import com.example.miPrimerAplicacion.InterfacesService.IEmpleadoService;
import com.example.miPrimerAplicacion.Model.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class Controler {
    @Autowired
    private IEmpleadoService service;


    @GetMapping("/listar")
    public String listar(Model model){
        List<Empleado>empleados= service.listar();
        model.addAttribute("empleados", empleados);
        return "FormWorker";
    }
    @GetMapping("/new")
    public String agregar(Model model){
        model.addAttribute("empleado", new Empleado());
        return "FormWorker";
    }

    @PostMapping("/save")
    public String save(@Validated Empleado p){
        service.save(p);
        return "redirect:/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model){
        Optional<Empleado> empleado = service.listarId(id);
        model.addAttribute("empleado", empleado);
        return "FormWorker";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(@PathVariable int id){
        service.delete(id);
        return "redirect:/listar";
    }

}

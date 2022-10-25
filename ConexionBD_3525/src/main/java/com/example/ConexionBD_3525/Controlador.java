package com.example.ConexionBD_3525;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Controlador {
    
    @Autowired
    private IProductoService service;
       
    @GetMapping("/")
    public String Mostrar(Model model)
    {
        List<Producto> productos = service.Listar();
        model.addAttribute("productos", productos);
        return "listaproducto"; //listaproducto.html
    }
    
    @GetMapping("/eliminar")
    public String Eliminar(@RequestParam("id") int id, Model model)
    {
        service.Eliminar(id);
        
        return Mostrar(model);
    }
    
    @GetMapping("/nuevo")
    public String Nuevo()
    {
        return "nuevo"; //nuevo.html
    }
    
    @PostMapping("/registrar")
    public String Registrar(@RequestParam("nom") String nom,
                            @RequestParam("desc") String desc,
                            @RequestParam("prec") float prec,
                            Model model)
    {
        Producto p = new Producto();
        p.setNombre(nom);
        p.setDescripcion(desc);
        p.setPrecio(prec);
        
        service.Guardar(p);
        
        return Mostrar(model);
    }
    
    @GetMapping("/editar")
    public String Editar(@RequestParam("id") int id, Model model)
    {
        Optional<Producto> producto = service.ConsultarId(id);
        model.addAttribute("producto", producto);
     
        return "editar";
    }
    
    @PostMapping("/actualizar")
    public String Actualizar(@RequestParam("id") int id,
                            @RequestParam("nombre") String nom,
                            @RequestParam("descripcion") String desc,
                            @RequestParam("precio") float prec,
                            Model model)
    {
        Producto p = new Producto();
        p.setId(id);
        p.setNombre(nom);
        p.setDescripcion(desc);
        p.setPrecio(prec);
        
        service.Guardar(p);
        
        return Mostrar(model);
    }
    
    @PostMapping("/buscar")
    public String Buscar(@RequestParam("dato") String dato, Model model)
    {
        List<Producto> productos = service.BuscarGeneral(dato);
        model.addAttribute("productos", productos);
        return "listaproducto";
    }
}

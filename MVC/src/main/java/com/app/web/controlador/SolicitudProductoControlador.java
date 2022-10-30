package com.app.web.controlador;

import com.app.web.entidad.Datacredito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

 
import com.app.web.repositorio.DatacreditoRepositorio;
import com.app.web.servicio.DatacreditoServicio;
 
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SolicitudProductoControlador {

        //servicios 
    
	@Autowired
        private DatacreditoServicio serviciodatacredito;
        
    
        
        //repositorios
         
        @Autowired
	private DatacreditoRepositorio datacreditorepositorio;
        
        
	 
        @GetMapping({ "/index", "/" })
	public String paginaIndex(Model modelo) {				 
            modelo.addAttribute("datacreditos", serviciodatacredito.listarTodosLosDatacredito());
                return "menulistadodatacredito";
	}
        
        
       
        
        @GetMapping("/menulistadodatacreditoeditar/{tipodocumento}/{documento}")         
	public String menulistadodatacreditoeditar(@PathVariable String tipodocumento,@PathVariable String documento, Model modelo ) 
        {	
            Datacredito datos= new Datacredito();            
            List<Datacredito> lista= datacreditorepositorio.BuscarListaNegra(tipodocumento  , documento);
            
            if (! lista.isEmpty())
            {
              datos= lista.get(0);
            }
            
            modelo.addAttribute("datacredito", datos ) ;            
            return "/menulistadodatacreditoeditar";
	}
        
        @PostMapping("/menulistadodatacreditoeditar")
	public String menulistadodatacreditoGuardar(@ModelAttribute("datacredito") Datacredito solicitud, Model modelo)
        {      
            serviciodatacredito.actualizarDatacredito(solicitud);             
            
             modelo.addAttribute("datacreditos", serviciodatacredito.listarTodosLosDatacredito());
                return "menulistadodatacredito";
	}
        
        
          @GetMapping("/menulistadodatacreditonuevo")         
	public String menulistadodatacreditonuevo( Model modelo ) 
        {	
            Datacredito datos= new Datacredito();                       
            
            modelo.addAttribute("datacredito", datos ) ;            
            return "/menulistadodatacreditonuevo";
	}
        
        @PostMapping("/menulistadodatacreditonuevo")
	public String menulistadodatacreditonuevoGuardar(@ModelAttribute("datacredito") Datacredito solicitud, Model modelo)
        {      
            serviciodatacredito.guardarDatacredito(solicitud);             
            
             modelo.addAttribute("datacreditos", serviciodatacredito.listarTodosLosDatacredito());
                return "menulistadodatacredito";
	}
        
        
 
    
    @PostMapping("/menulistadodatacreditoborrar/{id}")
    public String menulistadodatacreditoborrar(@PathVariable Long id){
        
        Optional<Datacredito> datos = datacreditorepositorio.findById(id) ;    
        datacreditorepositorio.delete(datos.get());
        
        return "redirect:/";
    }
        
        
        
        
        
}

package com.crudgames.spring.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import com.crudgames.spring.modelo.Amiga;
import com.crudgames.spring.modelo.Amstrad_cpc;
import com.crudgames.spring.modelo.Atari_2600;
import com.crudgames.spring.modelo.Coleccion;
import com.crudgames.spring.modelo.Compra_Venta;
import com.crudgames.spring.modelo.Famicom;
import com.crudgames.spring.modelo.Master_System;
import com.crudgames.spring.modelo.Megadrive_Genesis;
import com.crudgames.spring.modelo.Neo_Geo;
import com.crudgames.spring.modelo.Neo_Geo_Pocket;
import com.crudgames.spring.modelo.Nes;
import com.crudgames.spring.modelo.User;
import com.crudgames.spring.servicios.AmigaServicio;
import com.crudgames.spring.servicios.Amstrad_cpcServicio;
import com.crudgames.spring.servicios.Atari_2600Servicio;
import com.crudgames.spring.servicios.ColeccionServicio;
import com.crudgames.spring.servicios.FamicomServicio;
import com.crudgames.spring.servicios.Compra_VentaServicio;
import com.crudgames.spring.servicios.Master_SystemServicio;
import com.crudgames.spring.servicios.Megadrive_GenesisServicio;
import com.crudgames.spring.servicios.Neo_GeoServicio;
import com.crudgames.spring.servicios.Neo_Geo_PocketServicio;
import com.crudgames.spring.servicios.NesServicio;
import com.crudgames.spring.servicios.UserServicio;
import com.crudgames.spring.upload.storage.StorageService;

import lombok.Data;


@Data
@Controller
public class Controlador {
	
	@Autowired
	AmigaServicio servicioAmiga;
	
	@Autowired
	Amstrad_cpcServicio servicioAmstrad_cpc;
	
	@Autowired
	Atari_2600Servicio servicioAtari_2600;
	
	@Autowired
	Compra_VentaServicio servicioCompra_Venta;
	
	@Autowired
	ColeccionServicio servicioColeccion;
	
	
	@Autowired
	FamicomServicio servicioFamicom;
	
	@Autowired
	Master_SystemServicio servicioMaster_System;
	
	@Autowired
	Megadrive_GenesisServicio servicioMegadrive_Genesis;
	
	@Autowired
	Neo_Geo_PocketServicio servicioNeo_Geo_Pocket;
	
	@Autowired
	Neo_GeoServicio servicioNeo_Geo;
	
	@Autowired
	NesServicio servicioNes;
	
	@Autowired
	UserServicio servicioUser;
	
	
	@Autowired
	private StorageService storageService;
	
	private String ficheroEnviado; //

    
// CREAR TÍTULO AMIGA
    
    @GetMapping({ "/amiga" })
    public String listado2(Model model) {
    	model.addAttribute("listaAmiga", servicioAmiga.findAll());
    	return "listadoAmiga";
    }

    @GetMapping("/amiga/new")
    public String nuevoAmigaForm(Model model) {
    	model.addAttribute("amigaForm", new Amiga());
    	return "formAmiga";
    }

    @PostMapping("/amiga/new/submit")
    public String nuevoAmigaSubmit(@Valid @ModelAttribute("amigaForm") Amiga nuevoAmiga,
    		BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

    	if (bindingResult.hasErrors()) {
    		return "formAmiga";
    	} else {
    		if (!file.isEmpty()) {
    			String image = storageService.store(file, nuevoAmiga.getTitle());
    			System.out.println("La imagen a guardar es : " + image);
    			nuevoAmiga.setImage(MvcUriComponentsBuilder
    					.fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
    		}
    		servicioAmiga.add(nuevoAmiga);
    		return "redirect:/amiga";
    	}
    }

    @GetMapping("/amiga/edit/{id}")
    public String editarAmigaForm(@PathVariable long id, Model model) {
    	Amiga amiga = servicioAmiga.findById(id);
    	if (amiga != null) {
    		ficheroEnviado=amiga.getImage(); //para controlar si cambia el fichero
    		model.addAttribute("amigaForm", amiga);
    		return "formAmiga";
    	} else {
    		return "redirect:/amiga/new";
    	}
    }

    @PostMapping("/amiga/edit/submit")
    public String editarAmigaPost(@Valid @ModelAttribute("amigaForm") Amiga nuevoAmiga,
    		BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

    	System.out.println(ficheroEnviado + ", " + file.getName());
    	if (bindingResult.hasErrors()) {			
    		return "formAmiga";	
    	} else {			
    		if (!file.isEmpty()) {
    			String image = storageService.store(file, nuevoAmiga.getTitle());
    			nuevoAmiga.setImage(MvcUriComponentsBuilder
    					.fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
    		}else {
    			nuevoAmiga.setImage(ficheroEnviado);
    		}
    		servicioAmiga.edit(nuevoAmiga);
    		return "redirect:/amiga";
    	}
    }

    @GetMapping("/amiga/delete/{id}")
    public String deleteAmiga(@PathVariable("id") int id){
    	servicioAmiga.deleteAmiga(id);
        return "redirect:/amiga";
    }
    
    
    

// CREAR TÍTULO AMSTRAD_CPC
    
    @GetMapping({ "/amstrad_cpc" })
    public String listado3(Model model) {
    	model.addAttribute("listaAmstrad_cpc", servicioAmstrad_cpc.findAll());
    	return "listadoAmstrad_cpc";
    }

    @GetMapping("/amstrad_cpc/new")
    public String nuevoAmstrad_cpcForm(Model model) {
    	model.addAttribute("amstrad_cpcForm", new Amstrad_cpc());
    	return "formAmstrad_cpc";
    }

    @PostMapping("/amstrad_cpc/new/submit")
    public String nuevoAmstrad_cpcSubmit(@Valid @ModelAttribute("amstrad_cpcForm") Amstrad_cpc nuevoAmstrad_cpc,
    		BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

    	if (bindingResult.hasErrors()) {
    		return "formAmstrad_cpc";
    	} else {
    		if (!file.isEmpty()) {
    			String image = storageService.store(file, nuevoAmstrad_cpc.getTitle());
    			System.out.println("La imagen a guardar es : " + image);
    			nuevoAmstrad_cpc.setImage(MvcUriComponentsBuilder
    					.fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
    		}
    		servicioAmstrad_cpc.add(nuevoAmstrad_cpc);
    		return "redirect:/amstrad_cpc";
    	}
    }

    @GetMapping("/amstrad_cpc/edit/{id}")
    public String editarAmstrad_cpcForm(@PathVariable long id, Model model) {
    	Amstrad_cpc amstrad_cpc = servicioAmstrad_cpc.findById(id);
    	if (amstrad_cpc != null) {
    		ficheroEnviado=amstrad_cpc.getImage(); //para controlar si cambia el fichero
    		model.addAttribute("amstrad_cpcForm", amstrad_cpc);
    		return "formAmstrad_cpc";
    	} else {
    		return "redirect:/amstrad_cpc/new";
    	}
    }

    @PostMapping("/amstrad_cpc/edit/submit")
    public String editarAmstrad_cpcPost(@Valid @ModelAttribute("amstrad_cpcForm") Amstrad_cpc nuevoAmstrad_cpc,
    		BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

    	System.out.println(ficheroEnviado + ", " + file.getName());
    	if (bindingResult.hasErrors()) {			
    		return "formAmstrad_cpc";	
    	} else {			
    		if (!file.isEmpty()) {
    			String image = storageService.store(file, nuevoAmstrad_cpc.getTitle());
    			nuevoAmstrad_cpc.setImage(MvcUriComponentsBuilder
    					.fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
    		}else {
    			nuevoAmstrad_cpc.setImage(ficheroEnviado);
    		}
    		servicioAmstrad_cpc.edit(nuevoAmstrad_cpc);
    		return "redirect:/amstrad_cpc";
    	}
    }

    @GetMapping("/amstrad_cpc/delete/{id}")
    public String deleteAmstrad_cpc(@PathVariable("id") int id){
    	servicioAmstrad_cpc.deleteAmstrad_cpc(id);
        return "redirect:/amstrad_cpc";
    }
   
    
// CREAR TÍTULO ATARI_2600
    
    @GetMapping({ "/atari_2600" })
    public String listado4(Model model) {
    	model.addAttribute("listaAtari_2600", servicioAtari_2600.findAll());
    	return "listadoAtari_2600";
    }

    @GetMapping("/atari_2600/new")
    public String nuevoAtari_2600Form(Model model) {
    	model.addAttribute("atari_2600Form", new Atari_2600());
    	return "formAtari_2600";
    }

    @PostMapping("/atari_2600/new/submit")
    public String nuevoAtari_2600Submit(@Valid @ModelAttribute("atari_2600Form") Atari_2600 nuevoAtari_2600,
    		BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

    	if (bindingResult.hasErrors()) {
    		return "formAtari_2600";
    	} else {
    		if (!file.isEmpty()) {
    			String image = storageService.store(file, nuevoAtari_2600.getTitle());
    			System.out.println("La imagen a guardar es : " + image);
    			nuevoAtari_2600.setImage(MvcUriComponentsBuilder
    					.fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
    		}
    		servicioAtari_2600.add(nuevoAtari_2600);
    		return "redirect:/atari_2600";
    	}
    }

    @GetMapping("/atari_2600/edit/{id}")
    public String editarAtari_2600Form(@PathVariable long id, Model model) {
    	Atari_2600 atari_2600 = servicioAtari_2600.findById(id);
    	if (atari_2600 != null) {
    		ficheroEnviado=atari_2600.getImage(); //para controlar si cambia el fichero
    		model.addAttribute("atari_2600Form", atari_2600);
    		return "formAtari_2600";
    	} else {
    		return "redirect:/atari_2600/new";
    	}
    }

    @PostMapping("/atari_2600/edit/submit")
    public String editarAtari_2600Post(@Valid @ModelAttribute("atari_2600Form") Atari_2600 nuevoAtari_2600,
    		BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

    	System.out.println(ficheroEnviado + ", " + file.getName());
    	if (bindingResult.hasErrors()) {			
    		return "formAtari_2600";	
    	} else {			
    		if (!file.isEmpty()) {
    			String image = storageService.store(file, nuevoAtari_2600.getTitle());
    			nuevoAtari_2600.setImage(MvcUriComponentsBuilder
    					.fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
    		}else {
    			nuevoAtari_2600.setImage(ficheroEnviado);
    		}
    		servicioAtari_2600.edit(nuevoAtari_2600);
    		return "redirect:/atari_2600";
    	}
    }

    @GetMapping("/atari_2600/delete/{id}")
    public String deleteAtari_2600(@PathVariable("id") int id){
    	servicioAtari_2600.deleteAtari_2600(id);
        return "redirect:/atari_2600";
    }
    
// CREAR NUEVA COLECCIÓN
    
    @GetMapping({ "/coleccion" })
    public String listadoColeccion(Model model) {
    	model.addAttribute("listaColeccion", servicioColeccion.findAll());
    	return "listadoColeccion";
    }

    @GetMapping("/coleccion/new")
    public String nuevoColeccionForm(Model model) {
    	model.addAttribute("coleccionForm", new Coleccion());
    	
    	model.addAttribute("juegosUser", servicioUser.findAll());
    	model.addAttribute("juegosAmiga", servicioAmiga.findAll());
    	model.addAttribute("juegosAmstrad_cpc", servicioAmstrad_cpc.findAll());
    	model.addAttribute("juegosAtari_2600", servicioAtari_2600.findAll());
    	model.addAttribute("juegosFamicom", servicioFamicom.findAll());
    	model.addAttribute("juegosMaster_System", servicioMaster_System.findAll());
    	model.addAttribute("juegosMegadrive_Genesis", servicioMegadrive_Genesis.findAll());
    	model.addAttribute("juegosNeo_Geo_Pocket", servicioNeo_Geo_Pocket.findAll());
    	model.addAttribute("juegosNeo_Geo", servicioNeo_Geo.findAll());
    	model.addAttribute("juegosNes", servicioNes.findAll());
    	
    	return "formColeccion";
    }

    @PostMapping("/coleccion/new/submit")
    public String nuevoColeccionSubmit(@Valid @ModelAttribute("coleccionForm") Coleccion nuevoColeccion,
    		 BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
    		return "formColeccion";
    	} else {
    		
    		}
    		servicioColeccion.add(nuevoColeccion);
    		return "redirect:/coleccion";
    	}
    
    
    @GetMapping("/coleccion/edit/{id}")
    public String editarColeccionForm(@PathVariable long id, Model model) {
    	Coleccion coleccion = servicioColeccion.findById(id);
    	if (coleccion != null) {
    		ficheroEnviado=coleccion.getTitle(); //para controlar si cambia el fichero
    		model.addAttribute("coleccionForm", coleccion);
    		
    	    model.addAttribute("juegosUser", servicioUser.findAll());
        	model.addAttribute("juegosAmiga", servicioAmiga.findAll());
        	model.addAttribute("juegosAmstrad_cpc", servicioAmstrad_cpc.findAll());
        	model.addAttribute("juegosAtari_2600", servicioAtari_2600.findAll());
        	model.addAttribute("juegosFamicom", servicioFamicom.findAll());
        	model.addAttribute("juegosMaster_System", servicioMaster_System.findAll());
        	model.addAttribute("juegosMegadrive_Genesis", servicioMegadrive_Genesis.findAll());
        	model.addAttribute("juegosNeo_Geo_Pocket", servicioNeo_Geo_Pocket.findAll());
        	model.addAttribute("juegosNeo_Geo", servicioNeo_Geo.findAll());
        	model.addAttribute("juegosNes", servicioNes.findAll());
        	
    		return "formColeccion";
    	} else {
    		return "redirect:/coleccion/new";
    	}
    }

    @PostMapping("/coleccion/edit/submit")
    public String editarColeccionPost(@Valid @ModelAttribute("coleccionForm") Coleccion nuevoColeccion,
    		 BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
    		return "formColeccion";
    	} else {
    		
    		}
    		servicioColeccion.add(nuevoColeccion);
    		return "redirect:/coleccion";
    	}


    @GetMapping("/coleccion/delete/{id}")
    public String deleteColeccion(@PathVariable("id") int id){
    	servicioColeccion.deleteColeccion(id);
    	return "redirect:/coleccion";
    }

    
 
    
    // CREAR TÍTULO FAMICOM
    
    @GetMapping({ "/famicom" })
    public String listado5(Model model) {
        model.addAttribute("listaFamicom", servicioFamicom.findAll());
        return "listadoFamicom";
    }

    @GetMapping("/famicom/new")
    public String nuevoFamicomForm(Model model) {
        model.addAttribute("famicomForm", new Famicom());
        return "formFamicom";
    }

    @PostMapping("/famicom/new/submit")
    public String nuevoFamicomSubmit(@Valid @ModelAttribute("famicomForm") Famicom nuevoFamicom,
            BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

        if (bindingResult.hasErrors()) {
            return "formFamicom";
        } else {
            if (!file.isEmpty()) {
                String image = storageService.store(file, nuevoFamicom.getTitle());
                System.out.println("La imagen a guardar es : " + image);
                nuevoFamicom.setImage(MvcUriComponentsBuilder
                        .fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
            }
            servicioFamicom.add(nuevoFamicom);
            return "redirect:/famicom";
        }
    }

    @GetMapping("/famicom/edit/{id}")
    public String editarFamicomForm(@PathVariable long id, Model model) {
        Famicom famicom = servicioFamicom.findById(id);
        if (famicom != null) {
            ficheroEnviado=famicom.getImage(); //para controlar si cambia el fichero
            model.addAttribute("famicomForm", famicom);
            return "formFamicom";
        } else {
            return "redirect:/famicom/new";
        }
    }

    @PostMapping("/famicom/edit/submit")
    public String editarFamicomPost(@Valid @ModelAttribute("famicomForm") Famicom nuevoFamicom,
            BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

        System.out.println(ficheroEnviado + ", " + file.getName());
        if (bindingResult.hasErrors()) {			
            return "formFamicom";	
        } else {			
            if (!file.isEmpty()) {
                String image = storageService.store(file, nuevoFamicom.getTitle());
                nuevoFamicom.setImage(MvcUriComponentsBuilder
                        .fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
            }else {
                nuevoFamicom.setImage(ficheroEnviado);
            }
            servicioFamicom.edit(nuevoFamicom);
            return "redirect:/famicom";
        }
    }
    

    @GetMapping("/famicom/delete/{id}")
    public String deleteFamicom(@PathVariable("id") int id){
        servicioFamicom.deleteFamicom(id);
        return "redirect:/famicom";
    } 
    
 // CREAR TÍTULO MASTER_SYSTEM
    
    @GetMapping({ "/master_system" })
    public String listado7(Model model) {
    	model.addAttribute("listaMaster_System", servicioMaster_System.findAll());
    	return "listadoMaster_System";
    }

    @GetMapping("/master_system/new")
    public String nuevoMaster_SystemForm(Model model) {
    	model.addAttribute("master_systemForm", new Master_System());
    	return "formMaster_System";
    }

    @PostMapping("/master_system/new/submit")
    public String nuevoMaster_SystemSubmit(@Valid @ModelAttribute("master_systemForm") Master_System nuevoMaster_System,
    		BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

    	if (bindingResult.hasErrors()) {
    		return "formMaster_System";
    	} else {
    		if (!file.isEmpty()) {
    			String image = storageService.store(file, nuevoMaster_System.getTitle());
    			System.out.println("La imagen a guardar es : " + image);
    			nuevoMaster_System.setImage(MvcUriComponentsBuilder
    					.fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
    		}
    		servicioMaster_System.add(nuevoMaster_System);
    		return "redirect:/master_system";
    	}
    }

    @GetMapping("/master_system/edit/{id}")
    public String editarMaster_SystemForm(@PathVariable long id, Model model) {
    	Master_System master_system = servicioMaster_System.findById(id);
    	if (master_system != null) {
    		ficheroEnviado=master_system.getImage(); //para controlar si cambia el fichero
    		model.addAttribute("master_systemForm", master_system);
    		return "formMaster_System";
    	} else {
    		return "redirect:/master_system/new";
    	}
    }

    @PostMapping("/master_system/edit/submit")
    public String editarMaster_SystemPost(@Valid @ModelAttribute("master_systemForm") Master_System nuevoMaster_System,
    		BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

    	System.out.println(ficheroEnviado + ", " + file.getName());
    	if (bindingResult.hasErrors()) {			
    		return "formMaster_System";	
    	} else {			
    		if (!file.isEmpty()) {
    			String image = storageService.store(file, nuevoMaster_System.getTitle());
    			nuevoMaster_System.setImage(MvcUriComponentsBuilder
    					.fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
    		}else {
    			nuevoMaster_System.setImage(ficheroEnviado);
    		}
    		servicioMaster_System.edit(nuevoMaster_System);
    		return "redirect:/master_system";
    	}
    }

    @GetMapping("/master_system/delete/{id}")
    public String deleteMaster_System(@PathVariable("id") int id){
    	servicioMaster_System.deleteMaster_System(id);
    	return "redirect:/master_system";
    } 
    
    
 // CREAR TÍTULO MEGADRIVE_GENESIS
    
    @GetMapping({ "/megadrive_genesis" })
    public String listado6(Model model) {
        model.addAttribute("listaMegadrive_Genesis", servicioMegadrive_Genesis.findAll());
        return "listadoMegadrive_Genesis";
    }

    @GetMapping("/megadrive_genesis/new")
    public String nuevoMegadrive_GenesisForm(Model model) {
        model.addAttribute("megadrive_genesisForm", new Megadrive_Genesis());
        return "formMegadrive_Genesis";
    }

    @PostMapping("/megadrive_genesis/new/submit")
    public String nuevoMegadrive_GenesisSubmit(@Valid @ModelAttribute("megadrive_genesisForm") Megadrive_Genesis nuevoMegadrive_Genesis,
            BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

        if (bindingResult.hasErrors()) {
            return "formMegadrive_Genesis";
        } else {
            if (!file.isEmpty()) {
                String image = storageService.store(file, nuevoMegadrive_Genesis.getTitle());
                System.out.println("La imagen a guardar es : " + image);
                nuevoMegadrive_Genesis.setImage(MvcUriComponentsBuilder
                        .fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
            }
            servicioMegadrive_Genesis.add(nuevoMegadrive_Genesis);
            return "redirect:/megadrive_genesis";
        }
    }

    @GetMapping("/megadrive_genesis/edit/{id}")
    public String editarMegadrive_GenesisForm(@PathVariable long id, Model model) {
        Megadrive_Genesis megadrive_genesis = servicioMegadrive_Genesis.findById(id);
        if (megadrive_genesis != null) {
            ficheroEnviado=megadrive_genesis.getImage(); //para controlar si cambia el fichero
            model.addAttribute("megadrive_genesisForm", megadrive_genesis);
            return "formMegadrive_Genesis";
        } else {
            return "redirect:/megadrive_genesis/new";
        }
    }

    @PostMapping("/megadrive_genesis/edit/submit")
    public String editarMegadrive_GenesisPost(@Valid @ModelAttribute("megadrive_genesisForm") Megadrive_Genesis nuevoMegadrive_Genesis,
            BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

        System.out.println(ficheroEnviado + ", " + file.getName());
        if (bindingResult.hasErrors()) {			
            return "formMegadrive_Genesis";	
        } else {			
            if (!file.isEmpty()) {
                String image = storageService.store(file, nuevoMegadrive_Genesis.getTitle());
                nuevoMegadrive_Genesis.setImage(MvcUriComponentsBuilder
                        .fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
            }else {
                nuevoMegadrive_Genesis.setImage(ficheroEnviado);
            }
            servicioMegadrive_Genesis.edit(nuevoMegadrive_Genesis);
            return "redirect:/megadrive_genesis";
        }
    }

    @GetMapping("/megadrive_genesis/delete/{id}")
    public String deleteMegadrive_Genesis(@PathVariable("id") int id){
        servicioMegadrive_Genesis.deleteMegadrive_Genesis(id);
        return "redirect:/megadrive_genesis";
    } 
    
    
 // CREAR TÍTULO NEO GEO POCKET
    
    @GetMapping({ "/neo_geo_pocket" })
    public String listado9(Model model) {
        model.addAttribute("listaNeo_Geo_Pocket", servicioNeo_Geo_Pocket.findAll());
        return "listadoNeo_Geo_Pocket";
    }

    @GetMapping("/neo_geo_pocket/new")
    public String nuevoNeo_Geo_PocketForm(Model model) {
        model.addAttribute("neo_geo_pocketForm", new Neo_Geo_Pocket());
        return "formNeo_Geo_Pocket";
    }

    @PostMapping("/neo_geo_pocket/new/submit")
    public String nuevoNeo_Geo_PocketSubmit(@Valid @ModelAttribute("neo_geo_pocketForm") Neo_Geo_Pocket nuevoNeo_Geo_Pocket,
            BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

        if (bindingResult.hasErrors()) {
            return "formNeo_Geo_Pocket";
        } else {
            if (!file.isEmpty()) {
                String image = storageService.store(file, nuevoNeo_Geo_Pocket.getTitle());
                System.out.println("La imagen a guardar es : " + image);
                nuevoNeo_Geo_Pocket.setImage(MvcUriComponentsBuilder
                        .fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
            }
            servicioNeo_Geo_Pocket.add(nuevoNeo_Geo_Pocket);
            return "redirect:/neo_geo_pocket";
        }
    }

    @GetMapping("/neo_geo_pocket/edit/{id}")
    public String editarNeo_Geo_PocketForm(@PathVariable long id, Model model) {
        Neo_Geo_Pocket neo_geo_pocket = servicioNeo_Geo_Pocket.findById(id);
        if (neo_geo_pocket != null) {
            ficheroEnviado=neo_geo_pocket.getImage(); //para controlar si cambia el fichero
            model.addAttribute("neo_geo_pocketForm", neo_geo_pocket);
            return "formNeo_Geo_Pocket";
        } else {
            return "redirect:/neo_geo_pocket/new";
        }
    }

    @PostMapping("/neo_geo_pocket/edit/submit")
    public String editarNeo_Geo_PocketPost(@Valid @ModelAttribute("neo_geo_pocketForm") Neo_Geo_Pocket nuevoNeo_Geo_Pocket,
            BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

        System.out.println(ficheroEnviado + ", " + file.getName());
        if (bindingResult.hasErrors()) {			
            return "formNeo_Geo_Pocket";	
        } else {			
            if (!file.isEmpty()) {
                String image = storageService.store(file, nuevoNeo_Geo_Pocket.getTitle());
                nuevoNeo_Geo_Pocket.setImage(MvcUriComponentsBuilder
                        .fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
            }else {
                nuevoNeo_Geo_Pocket.setImage(ficheroEnviado);
            }
            servicioNeo_Geo_Pocket.edit(nuevoNeo_Geo_Pocket);
            return "redirect:/neo_geo_pocket";
        }
    }

    @GetMapping("/neo_geo_pocket/delete/{id}")
    public String deleteNeo_Geo_Pocket(@PathVariable("id") int id){
        servicioNeo_Geo_Pocket.deleteNeo_Geo_Pocket(id);
        return "redirect:/neo_geo_pocket";
    } 
    
    // CREAR TÍTULO NEO GEO 
    
    @GetMapping({ "/neo_geo" })
    public String listado10(Model model) {
        model.addAttribute("listaNeo_Geo", servicioNeo_Geo.findAll());
        return "listadoNeo_Geo";
    }

    @GetMapping("/neo_geo/new")
    public String nuevoNeo_GeoForm(Model model) {
        model.addAttribute("neo_geoForm", new Neo_Geo());
        return "formNeo_Geo";
    }

    @PostMapping("/neo_geo/new/submit")
    public String nuevoNeo_GeoSubmit(@Valid @ModelAttribute("neo_geoForm") Neo_Geo nuevoNeo_Geo,
            BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

        if (bindingResult.hasErrors()) {
            return "formNeo_Geo";
        } else {
            if (!file.isEmpty()) {
                String image = storageService.store(file, nuevoNeo_Geo.getTitle());
                System.out.println("La imagen a guardar es : " + image);
                nuevoNeo_Geo.setImage(MvcUriComponentsBuilder
                        .fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
            }
            servicioNeo_Geo.add(nuevoNeo_Geo);
            return "redirect:/neo_geo";
        }
    }

    @GetMapping("/neo_geo/edit/{id}")
    public String editarNeo_GeoForm(@PathVariable long id, Model model) {
        Neo_Geo neo_geo = servicioNeo_Geo.findById(id);
        if (neo_geo != null) {
            ficheroEnviado=neo_geo.getImage(); //para controlar si cambia el fichero
            model.addAttribute("neo_geoForm", neo_geo);
            return "formNeo_Geo";
        } else {
            return "redirect:/neo_geo/new";
        }
    }

    @PostMapping("/neo_geo/edit/submit")
    public String editarNeo_GeoPost(@Valid @ModelAttribute("neo_geoForm") Neo_Geo nuevoNeo_Geo,
            BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

        System.out.println(ficheroEnviado + ", " + file.getName());
        if (bindingResult.hasErrors()) {			
            return "formNeo_Geo";	
        } else {			
            if (!file.isEmpty()) {
                String image = storageService.store(file, nuevoNeo_Geo.getTitle());
                nuevoNeo_Geo.setImage(MvcUriComponentsBuilder
                        .fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
            }else {
                nuevoNeo_Geo.setImage(ficheroEnviado);
            }
            servicioNeo_Geo.edit(nuevoNeo_Geo);
            return "redirect:/neo_geo";
        }
    }

    @GetMapping("/neo_geo/delete/{id}")
    public String deleteNeo_Geo(@PathVariable("id") int id){
        servicioNeo_Geo.deleteNeo_Geo(id);
        return "redirect:/neo_geo";
    } 
    
// CREAR TÃ�TULO NES
    
    @GetMapping({ "/nes" })
    public String listado12(Model model) {
        model.addAttribute("listaNes", servicioNes.findAll());
        return "listadoNes";
    }

    @GetMapping("/nes/new")
    public String nuevoNesForm(Model model) {
        model.addAttribute("nesForm", new Nes());
        return "formNes";
    }

    @PostMapping("/nes/new/submit")
    public String nuevoNesSubmit(@Valid @ModelAttribute("nesForm") Nes nuevoNes,
            BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

        if (bindingResult.hasErrors()) {
            return "formNes";
        } else {
            if (!file.isEmpty()) {
                String image = storageService.store(file, nuevoNes.getTitle());
                System.out.println("La imagen a guardar es : " + image);
                nuevoNes.setImage(MvcUriComponentsBuilder
                        .fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
            }
            servicioNes.add(nuevoNes);
            return "redirect:/nes";
        }
    }

    @GetMapping("/nes/edit/{id}")
    public String editarNesForm(@PathVariable long id, Model model) {
        Nes nes = servicioNes.findById(id);
        if (nes != null) {
            ficheroEnviado=nes.getImage(); //para controlar si cambia el fichero
            model.addAttribute("nesForm", nes);
            return "formNes";
        } else {
            return "redirect:/nes/new";
        }
    }

    @PostMapping("/nes/edit/submit")
    public String editarNesPost(@Valid @ModelAttribute("nesForm") Nes nuevoNes,
            BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

        System.out.println(ficheroEnviado + ", " + file.getName());
        if (bindingResult.hasErrors()) {			
            return "formNes";	
        } else {			
            if (!file.isEmpty()) {
                String image = storageService.store(file, nuevoNes.getTitle());
                nuevoNes.setImage(MvcUriComponentsBuilder
                        .fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
            }else {
                nuevoNes.setImage(ficheroEnviado);
            }
            servicioNes.edit(nuevoNes);
            return "redirect:/nes";
        }
    }

    @GetMapping("/nes/delete/{id}")
    public String deleteNes(@PathVariable("id") int id){
        servicioNes.deleteNes(id);
        return "redirect:/nes";
    } 
    
    
    // COMPRA-VENTA
    
    @GetMapping({ "/compra_venta" })
    public String listado13(Model model) {
        model.addAttribute("listaCompra_Venta", servicioCompra_Venta.findAll());
        return "listadoCompra_Venta";
    }

    @GetMapping("/compra_venta/new")
    public String nuevoCompra_VentaForm(Model model) {
        model.addAttribute("compra_ventaForm", new Compra_Venta());
        return "formCompra_Venta";
    }

    @PostMapping("/compra_venta/new/submit")
    public String nuevoCompra_VentaSubmit(@Valid @ModelAttribute("compra_ventaForm") Compra_Venta nuevoCompra_Venta,
            BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

        if (bindingResult.hasErrors()) {
            return "formCompra_Venta";
        } else {
            if (!file.isEmpty()) {
                String image = storageService.store(file, nuevoCompra_Venta.getArticulo());
                System.out.println("La imagen a guardar es : " + image);
                nuevoCompra_Venta.setImage(MvcUriComponentsBuilder
                        .fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
            }
            servicioCompra_Venta.add(nuevoCompra_Venta);
            return "redirect:/compra_venta";
        }
    }

    @GetMapping("/compra_venta/edit/{id}")
    public String editarCompra_VentaForm(@PathVariable long id, Model model) {
        Compra_Venta compra_venta = servicioCompra_Venta.findById(id);
        if (compra_venta != null) {
            ficheroEnviado=compra_venta.getImage(); //para controlar si cambia el fichero
            model.addAttribute("compra_ventaForm", compra_venta);
            return "formCompra_Venta";
        } else {
            return "redirect:/compra_venta/new";
        }
    }

    @PostMapping("/compra_venta/edit/submit")
    public String editarCompra_VentaPost(@Valid @ModelAttribute("compra_ventaForm") Compra_Venta nuevoCompra_Venta,
            BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

        System.out.println(ficheroEnviado + ", " + file.getName());
        if (bindingResult.hasErrors()) {			
            return "formCompra_Venta";	
        } else {			
            if (!file.isEmpty()) {
                String image = storageService.store(file, nuevoCompra_Venta.getArticulo());
                nuevoCompra_Venta.setImage(MvcUriComponentsBuilder
                        .fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
            }else {
                nuevoCompra_Venta.setImage(ficheroEnviado);
            }
            servicioCompra_Venta.edit(nuevoCompra_Venta);
            return "redirect:/compra_venta";
        }
    }

    @GetMapping("/compra_venta/delete/{id}")
    public String deleteCompra_Venta(@PathVariable("id") int id){
        servicioCompra_Venta.deleteCompra_Venta(id);
        return "redirect:/compra_venta";
    } 
    
    

 // CREAR TÍTULO USUARIO

    @GetMapping({ "/user" })
    public String listado125(Model model) {
        model.addAttribute("listaUser", servicioUser.findAll());
        return "listadoUser";
    }

    @GetMapping("/user/new")
    public String nuevoUserForm(Model model) {
        model.addAttribute("userForm", new User());
        return "formUser";
    }

    @PostMapping("/user/new/submit")
    public String nuevoUserSubmit(@Valid @ModelAttribute("userForm") User nuevoUser,
            BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

        if (bindingResult.hasErrors()) {
            return "formUser";
        } else {
            if (!file.isEmpty()) {
                String image = storageService.store(file, nuevoUser.getEmail());
                System.out.println("La imagen a guardar es : " + image);
                nuevoUser.setEmail(MvcUriComponentsBuilder
                        .fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
            }
            servicioUser.add(nuevoUser);
            return "redirect:/user";
        }
    }

    @GetMapping("/user/edit/{id}")
    public String editarUserForm(@PathVariable long id, Model model) {
        User user = servicioUser.findById(id);
        if (user != null) {
            ficheroEnviado=user.getEmail(); //para controlar si cambia el fichero
            model.addAttribute("userForm", user);
            return "formUser";
        } else {
            return "redirect:/user/new";
        }
    }

    @PostMapping("/user/edit/submit")
    public String editarUserPost(@Valid @ModelAttribute("userForm") User nuevoUser,
            BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

        System.out.println(ficheroEnviado + ", " + file.getName());
        if (bindingResult.hasErrors()) {			
            return "formUser";	
        } else {			
            if (!file.isEmpty()) {
                String image = storageService.store(file, nuevoUser.getEmail());
                nuevoUser.setEmail(MvcUriComponentsBuilder
                        .fromMethodName(Controlador.class, "serveFile", image).build().toUriString());
            }else {
                nuevoUser.setEmail(ficheroEnviado);
            }
            servicioUser.edit(nuevoUser);
            return "redirect:/user";
        }
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
        servicioUser.deleteUser(id);
        return "redirect:/user";
    } 

@GetMapping("/files/{filename:.+}")
@ResponseBody
public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
	Resource file = storageService.loadAsResource(filename);
	return ResponseEntity.ok().body(file);
}

} 


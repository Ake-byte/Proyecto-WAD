package com.proyectowad.app.controladorweb;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.proyectowad.app.modelo.entidades.Categoria;
import com.proyectowad.app.modelo.entidades.Producto;
import com.proyectowad.app.modelo.service.IProductoService;
import com.proyectowad.app.modelo.service.IUploadFileService;
import com.proyectowad.app.modelo.util.PageRender;

@Controller("/producto")
@SessionAttributes("producto")
public class ProductoController {
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	/*@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;
		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}*/
	
	@GetMapping(value = "/verProducto/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Producto producto = productoService.findOne(id);
		if (producto == null) {
			return "redirect:/listarProducto";
		}
		
		model.put("producto", producto);
		model.put("titulo", "Detalle producto: " + producto.getNombre());

		return "verProducto";
	}

	@RequestMapping(value = "/listarProducto", method = RequestMethod.GET)
	public String listar(@RequestParam(name="page",defaultValue="0") int page,Model model) {
		Pageable pageRequest = PageRequest.of(page,10);
		
		Page<Producto> producto = productoService.findAll(pageRequest);
		
		PageRender<Producto> pageRender = new PageRender<Producto>("/listarProducto", producto);
		
		model.addAttribute("titulo", "Listado de productos");
		model.addAttribute("productos", productoService.findAll());
		model.addAttribute("page", pageRender);
		return "listarProducto";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/formProducto")
	public String crear(Map<String, Object> model) {

		Producto producto = new Producto();
		model.put("producto", producto);
		model.put("titulo", "Formulario de producto");
		return "formProducto";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/formProducto/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Producto producto = null;
		if (id > 0) {
			producto = productoService.findOne(id);
		} else {
			return "redirect:/listarProducto";
		}
		model.put("producto", producto);
		model.put("titulo", "Editar producto");
		return "formProducto";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/formProducto", method = RequestMethod.POST)
	public String guardar(@Valid Producto producto, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de producto");
			return "formProducto";
		}

		/*if (!foto.isEmpty()) {

			if (producto.getId() != null && producto.getId() > 0 && producto.getFoto() != null
					&& producto.getFoto().length() > 0) {

				uploadFileService.delete(producto.getFoto());
			}
			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			producto.setFoto(uniqueFilename);
		} else {
			producto.setFoto("");
		}*/
	
		productoService.save(producto);
	status.setComplete();
	
	return"redirect:listarProducto";

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminarProducto/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if (id > 0) {
			Producto producto = productoService.findOne(id);
			productoService.delete(id);
			
			/*if(producto.getFoto() != null) {
				uploadFileService.delete(producto.getFoto());
			}*/

		}
		return "redirect:/listarProducto";
	}
}

package com.proyectowad.app.controladorweb;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
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
import com.proyectowad.app.modelo.service.ICategoriaService;
import com.proyectowad.app.modelo.service.IUploadFileService;
import com.proyectowad.app.modelo.util.PageRender;

@Controller("/categoria")
@SessionAttributes("categoria")
public class CategoriaController {

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ICategoriaService categoriaService;
	
	@Autowired
	private IUploadFileService uploadFileService;
	
	
	@GetMapping(value = "/uploads/{filename:.+}")
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
	}
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Categoria categoria = categoriaService.findOne(id);
		if (categoria == null) {
			return "redirect:/listar";
		}
		
		model.put("categoria", categoria);
		model.put("titulo", "Detalle categoria: " + categoria.getNombre());

		return "ver";
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name="page",defaultValue="0") int page,Model model,
			Authentication authentication,
			HttpServletRequest request) {
		
		if(authentication != null) {
			logger.info("Hola usuario " + authentication.getName());
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null) {
			logger.info("Hola usuario " + auth.getName());
		}
		
		if(hasRole("ROLE_ADMIN")) {
			logger.info("Hola " + auth.getName() + " tienes acceso");
		} else {
			logger.info("Hola " + auth.getName() + " NO tienes acceso");
		}
		
		SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request, "ROLE_");
		
		if(securityContext.isUserInRole("ADMIN")) {
			logger.info("Forma AwareRWrapper " + auth.getName() + " tienes acceso");
		}
		else {
			logger.info("Forma AwareRWrapper" + auth.getName() + " NO tienes acceso");
		}
		
		if(request.isUserInRole("ROLE_ADMIN")) {
			logger.info("Forma HTTP " + auth.getName() + " tienes acceso");
		}
		else {
			logger.info("Forma HTTP" + auth.getName() + " NO tienes acceso");
		}
		
		Pageable pageRequest = PageRequest.of(page,5);
		
		Page<Categoria> categoria = categoriaService.findAll(pageRequest);
		
		PageRender<Categoria> pageRender = new PageRender<Categoria>("/listar", categoria);
		model.addAttribute("titulo", "Listado de categorías");
		model.addAttribute("categorias", categoria);
		model.addAttribute("page", pageRender);
		return "listar";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Categoria categoria = new Categoria();
		model.put("categoria", categoria);
		model.put("titulo", "Formulario de Categoría");
		return "form";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Categoria categoria = null;
		if (id > 0) {
			categoria = categoriaService.findOne(id);
		} else {
			return "redirect:/listar";
		}
		model.put("categoria", categoria);
		model.put("titulo", "Editar Categoría");
		return "form";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Categoria categoria, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Categoría");
			return "form";
		}

		if (!foto.isEmpty()) {

			if (categoria.getId() != null && categoria.getId() > 0 && categoria.getFoto() != null
					&& categoria.getFoto().length() > 0) {

				uploadFileService.delete(categoria.getFoto());
			}
			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			categoria.setFoto(uniqueFilename);
		} else {
			categoria.setFoto("");
		}
	
		categoriaService.save(categoria);
	status.setComplete();
	
	return"redirect:listar";

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if (id > 0) {
			Categoria categoria = categoriaService.findOne(id);
			categoriaService.delete(id);
			
			if(categoria.getFoto() != null) {
				uploadFileService.delete(categoria.getFoto());
			}

		}
		return "redirect:/listar";
	}
	
	private boolean hasRole(String role) {
		SecurityContext context = SecurityContextHolder.getContext();
		
		if(context == null) {
			return false;
		}
		Authentication auth = context.getAuthentication();
		
		if(auth == null) {
			return false;
		}
		
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		
		return authorities.contains(new SimpleGrantedAuthority(role));
		
		/*for(GrantedAuthority authority: authorities) {
			if(role.equals(authority.getAuthority())) {
				logger.info("Hola " + auth.getName() + " tu rol es: " + authority.getAuthority());
				return true;
			}
		}
		
		return false;*/
	}
}


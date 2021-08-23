package br.com.dataagil.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.dataagil.DTO.DeveloperDto;
import br.com.dataagil.entity.Developer;
import br.com.dataagil.exception.ServiceException;
import br.com.dataagil.service.DeveloperService;


@Scope(value="request")
@Controller
public class DeveloperController {
	
	List<Developer> listaDev = new ArrayList<Developer>();
	
	@Autowired
	DeveloperService developerService;
	
	@PostConstruct
	public void init(){
		listaDev =  developerService.buscarTodos();
	}

	public void importarDadosApiGit() {
		
		Developer developer = new Developer();
		
		RestTemplate restTemplate = new RestTemplate();
		String teste = restTemplate.getForObject("https://api.github.com/users", String.class);	
		
		Gson g = new Gson();
		
		Type collectionType = new TypeToken<List<DeveloperDto>>(){}.getType();
		List<DeveloperDto> devs  = g.fromJson( teste, collectionType);
	

		for (DeveloperDto var : devs) {
			developer.setAvatarUrl(var.getAvatarUrl());
			developer.setEventsUrl(var.getEventsUrl());
			developer.setFollowersUrl(var.getFollowersUrl());
			developer.setFollowingUrl(var.getFollowingUrl());
			developer.setGistsUrl(var.getGistsUrl());
			developer.setGravatarId(var.getGravatarId());
			developer.setHtmlUrl(var.getHtmlUrl());
			developer.setId(var.getId());
			developer.setLogin(var.getLogin());
			developer.setNodeId(var.getNodeId());
			developer.setOrganizationsUrl(var.getOrganizationsUrl());
			developer.setReceivedEventsUrl(var.getReceivedEventsUrl());
			developer.setReposUrl(var.getReposUrl());
			developer.setScore(var.getScore());
			developer.setSiteAdmin(var.getSiteAdmin());
			developer.setStarredUrl(var.getStarredUrl());
			developer.setSubscriptionsUrl(var.getSubscriptionsUrl());
			developer.setType(var.getType());
			developer.setUrl(var.getUrl());
			
			try {
				developerService.salvar(developer);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			
		}
		
		
		listaDev = developerService.buscarTodos();
		
	}

	public List<Developer> getListaDev() {
		return listaDev;
	}

	public void setListaDev(List<Developer> listaDev) {
		this.listaDev = listaDev;
	}
	 
	 
	
	
	
	
	
}



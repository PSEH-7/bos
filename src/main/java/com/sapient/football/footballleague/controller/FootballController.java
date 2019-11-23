package com.sapient.football.footballleague.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sapient.football.footballleague.conf.ApiConfiguration;

@RestController
public class FootballController {
	@Autowired
	private ApiConfiguration api;

	@RequestMapping("/country")
	private String country() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);
		RestTemplate rest = new RestTemplate();
		Map<String, String> parm = new HashMap<>();
		parm.put("action", "get_countries");
		parm.put("APIkey", api.getKey());
		String url = api.getUrl() + "?action=get_countries&APIkey=" + api.getKey();
		ResponseEntity<String> response = rest.exchange(url, HttpMethod.GET, entity, String.class);
		return response.toString();
	}

	@RequestMapping("/league")
	private String league(@RequestParam(value = "country") String country) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);
		RestTemplate rest = new RestTemplate();
		String url = api.getUrl() + "?action=get_leagues&APIkey=" + api.getKey();
		if (country != null) {
			url = url + "&country_id=" + country;
		}
		ResponseEntity<String> response = rest.exchange(url, HttpMethod.GET, entity, String.class);
		return response.toString();
	}

	@RequestMapping("/team")
	private String team(@RequestParam(value = "team",required=false) String team, @RequestParam(value = "league",required=false ) String league) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);
		RestTemplate rest = new RestTemplate();
		String url = api.getUrl() + "?action=get_teams&APIkey=" + api.getKey();
		if (team != null) {
			url = url + "&team_id=" + team;
		}
		if (league != null) {
			url = url + "&league_id=" + league;
		}

		ResponseEntity<String> response = rest.exchange(url, HttpMethod.GET, entity, String.class);
		return response.toString();
	}

	@RequestMapping("/player")
	private String player(@RequestParam(value = "id",required=false) String id, @RequestParam(value = "name",required=false) String name) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(headers);
		RestTemplate rest = new RestTemplate();
		String url = api.getUrl() + "?action=get_players&APIkey=" + api.getKey();
		if (id != null) {
			url = url + "&team_id=" + id;
		}
		if (name != null) {
			url = url + "&league_id=" + name;
		}

		ResponseEntity<String> response = rest.exchange(url, HttpMethod.GET, entity, String.class);
		return response.toString();
	}

}

package com.ps.fbl.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ps.fbl.model.Country;
import com.ps.fbl.model.Leagues;
import com.ps.fbl.model.TeamStanding;
import com.ps.fbl.util.Action;
/**
 * 
 * @author Mukesh.V
 *
 */
@Service
public class FBLClient {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${api.key}")
	private String api;

	@Value("${base.url}")
	private String baseUrl;


	public Country[] getCountries() {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("action", Action.COUNTRIES.getValue());
		UriComponentsBuilder builder = getUriComponentsBuilder(baseUrl, queryParams);
		return this.restTemplate
				.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(getHeaders()), Country[].class)
				.getBody();
	}

	public Leagues[] getLeagues(int countryId) {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("action", Action.LEAGUES.getValue());
		queryParams.put("country_id", String.valueOf(countryId));
		UriComponentsBuilder builder = getUriComponentsBuilder(baseUrl, queryParams);
		return this.restTemplate
				.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(getHeaders()), Leagues[].class)
				.getBody();
	}

	public TeamStanding[] getTeamStanding(int leagueId) {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("action", Action.STANDINGS.getValue());
		queryParams.put("league_id", String.valueOf(leagueId));
		UriComponentsBuilder builder = getUriComponentsBuilder(baseUrl, queryParams);
		return this.restTemplate
				.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(getHeaders()), TeamStanding[].class)
				.getBody();
	}

	private UriComponentsBuilder getUriComponentsBuilder(String url, Map<String, String> queryParams) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("APIkey", api);
		queryParams.forEach(builder::queryParam);
		return builder;
	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}
}

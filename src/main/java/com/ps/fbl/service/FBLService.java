package com.ps.fbl.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.fbl.client.FBLClient;
import com.ps.fbl.dto.FBLDto;
import com.ps.fbl.exception.FBLException;
import com.ps.fbl.model.Country;
import com.ps.fbl.model.Leagues;
import com.ps.fbl.model.TeamStanding;
import com.ps.fbl.model.TeamStandingRequest;
import com.ps.fbl.util.FBLLogger;

/**
 * 
 * @author Mukesh.V
 *
 */
@Service
public class FBLService {

	@Autowired
	private FBLClient fBLClient;

	public FBLDto getTeamStanding(TeamStandingRequest teamStandingRequest) throws FBLException {
		// Validate the request
		TeamStanding teamStandingDefault = getDefaultTeamStanding(teamStandingRequest);
		List<Country> countries = getCountries();
		Country country = getCountryByName(teamStandingRequest, countries);
		if (!isValidateCountryResponse(teamStandingRequest, teamStandingDefault, country)) {
			return FBLDto.from(teamStandingDefault);
		}
		teamStandingDefault.setCountryId(country.getId());

		List<Leagues> leaguesList = getLeagues(country.getId());
		Leagues leagues = getLeaguesByName(teamStandingRequest, leaguesList);
		if (!isValidLeagueResponse(teamStandingRequest, teamStandingDefault, leagues)) {
			return (FBLDto.from(teamStandingDefault));
		}
		teamStandingDefault.setLeagueId(leagues.getLeagueId());
		List<TeamStanding> teamStandings = getTeamStanding(leagues.getLeagueId());
		FBLLogger.info("team standing found " + teamStandings.toString());

		TeamStanding teamStandingsFiltered = getFilteredTeamStanding(teamStandingRequest, teamStandings);
		teamStandingsFiltered.setCountryId(country.getId());
		FBLLogger.info("team standing filtered found " + teamStandingsFiltered.toString());
		if (teamStandingsFiltered.getTeamId() == 0) {
			return FBLDto.from(teamStandingDefault);
		}

		return FBLDto.from(teamStandingsFiltered);
	}

	private Country getCountryByName(TeamStandingRequest teamStandingRequest, List<Country> countries) {
		return countries.stream().filter(c -> teamStandingRequest.getCountryName().equalsIgnoreCase(c.getName()))
				.findFirst().orElse(null);
	}

	private Leagues getLeaguesByName(TeamStandingRequest teamStandingRequest, List<Leagues> leaguesList) {
		return leaguesList.stream().filter(l -> teamStandingRequest.getLeagueName().equalsIgnoreCase(l.getLeagueName()))
				.findFirst().orElse(null);
	}

	private TeamStanding getFilteredTeamStanding(TeamStandingRequest teamStandingRequest,
			List<TeamStanding> teamStandings) {
		return teamStandings.stream().filter(t -> teamStandingRequest.getTeamName().equalsIgnoreCase(t.getTeamName()))
				.findFirst().orElse(null);
	}

	private boolean isValidLeagueResponse(TeamStandingRequest teamStandingRequest, TeamStanding teamStandingDefault,
			Leagues leagues) throws FBLException {
		if (Objects.isNull(leagues)) {
			throw new FBLException("leagues Not Found by name " + teamStandingRequest.getLeagueName());
		}
		FBLLogger.info("league found " + leagues.toString());
		if (leagues.getLeagueId() == 0) {
			return false;
		}
		return true;
	}

	private boolean isValidateCountryResponse(TeamStandingRequest teamStandingRequest, TeamStanding teamStandingDefault,
			Country country) throws FBLException {
		if (Objects.isNull(country)) {
			throw new FBLException("Country Not Found by name " + teamStandingRequest.getCountryName());
		}
		FBLLogger.info("Country found " + country.toString());

		if (country.getId() == 0) {
			teamStandingDefault.setCountryId(0);
			return false;
		}
		return true;
	}

	private TeamStanding getDefaultTeamStanding(TeamStandingRequest teamStandingRequest) {
		TeamStanding teamStanding = new TeamStanding();
		teamStanding.setTeamName(teamStandingRequest.getTeamName());
		teamStanding.setCountryName(teamStandingRequest.getCountryName());
		teamStanding.setLeagueName(teamStandingRequest.getLeagueName());
		return teamStanding;
	}

	private List<Country> getCountries() {
		return new ArrayList<>(Arrays.asList(fBLClient.getCountries()));
	}

	private List<Leagues> getLeagues(int countryId) {
		return new ArrayList<>(Arrays.asList(fBLClient.getLeagues(countryId)));
	}

	private List<TeamStanding> getTeamStanding(int leagueId) {
		return new ArrayList<>(Arrays.asList(fBLClient.getTeamStanding(leagueId)));
	}

}

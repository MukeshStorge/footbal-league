package com.ps.fbl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.fbl.dto.FBLDto;
import com.ps.fbl.exception.FBLException;
import com.ps.fbl.model.TeamStandingRequest;
import com.ps.fbl.service.FBLService;

@RestController
@RequestMapping("/api/service/v1/team/standing")
public class FBLController {

	@Autowired
	private FBLService fBLService;

	@Autowired
	public FBLController(FBLService fBLService) {
		this.fBLService = fBLService;
	}

	@PostMapping
	public ResponseEntity<FBLDto> getStandings(@RequestBody TeamStandingRequest teamStandingRequest) throws FBLException {
		return ResponseEntity.ok(fBLService.getTeamStanding(teamStandingRequest));
	}

}

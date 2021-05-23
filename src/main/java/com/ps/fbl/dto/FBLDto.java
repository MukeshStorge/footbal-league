package com.ps.fbl.dto;

import java.util.Objects;

import com.ps.fbl.model.TeamStanding;

import lombok.Data;

@Data
public class FBLDto {

  private String country;
  private String league;
  private String team;
  private int overallPosition;

  public static FBLDto from(TeamStanding teamStanding) {
    FBLDto dto = new FBLDto();
    if (Objects.nonNull(teamStanding)) {
      dto.setCountry("(" + teamStanding.getCountryId() + ") - " + teamStanding.getCountryName());
      dto.setLeague("(" + teamStanding.getLeagueId() + ") - " + teamStanding.getLeagueName());
      dto.setTeam("(" + teamStanding.getTeamId() + ") - " + teamStanding.getTeamName());
      dto.setOverallPosition(teamStanding.getOverallPosition());
    }
    return dto;

  }
}

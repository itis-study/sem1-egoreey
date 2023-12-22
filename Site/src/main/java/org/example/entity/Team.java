package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private Integer id;
    private String name;
    private String usersId;
    private String tasksId;
    private Integer team_admin;
}

package me.bnnq.homework.configs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DatabaseConfiguration
{
    private String url;
    private String databaseName;
    private String username;
    private String password;
}

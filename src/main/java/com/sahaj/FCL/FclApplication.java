package com.sahaj.FCL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FclApplication implements CommandLineRunner {


    @Value("${input_file_path}")
    private String filePath;

    @Autowired
    private WinnerCheckService winnerCheckService;

    public static void main(String[] args) {
        SpringApplication.run(FclApplication.class, args);
    }

    @Override
    public void run(String... args) {
        var teamName = winnerCheckService.readAllLines(filePath);
        System.out.println("Winning team is :: " + teamName);

    }
}

package com.sahaj.FCL;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Player {
    private String id;
    private String name;
    private int runs;
    private int wickets;
}

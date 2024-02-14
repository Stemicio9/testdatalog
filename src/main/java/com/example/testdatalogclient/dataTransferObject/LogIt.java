package com.example.testdatalogclient.dataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogIt {
    private String category;
    private String msg;
    private Long timestamp;
}

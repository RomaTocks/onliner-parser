package com.onliner.tocks.parsing.common.tdp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("tdp")
public class TDP {
    @Id
    private String id;
    private String name;
    private String tdp;
    private Integer tdpValue;

}

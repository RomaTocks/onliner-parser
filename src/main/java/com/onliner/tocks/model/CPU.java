package com.onliner.tocks.model;

import com.onliner.tocks.model.additional.CPUAdditionalInformation;
import com.onliner.tocks.parsing.common.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "processors")
public class CPU extends Product {
    private CPUAdditionalInformation additional;

    @Override
    public String toString()
    {
        return "CPU{" +
                "additional=" + additional +
                ", id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", prices=" + prices +
                ", html_url='" + html_url + '\'' +
                ", sellers=" + sellers +
                '}';
    }
}

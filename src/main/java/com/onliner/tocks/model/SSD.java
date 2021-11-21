package com.onliner.tocks.model;

import com.onliner.tocks.model.additional.SSDAdditionalInformation;
import com.onliner.tocks.parsing.common.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ssd")
public class SSD extends Product {
    private SSDAdditionalInformation additional;
    @Override
    public String toString() {
        return "SSD{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", prices=" + prices +
                ", html_url='" + html_url + '\'' +
                '}';
    }
}

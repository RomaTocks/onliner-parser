package com.onliner.tocks.model;


import com.onliner.tocks.model.additional.GraphicCardAdditionalInformation;
import com.onliner.tocks.parsing.common.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "graphic-cards")
public class GraphicCard extends Product {
    private GraphicCardAdditionalInformation additional;

    @Override
    public String toString()
    {
        return "GraphicCard{" +
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

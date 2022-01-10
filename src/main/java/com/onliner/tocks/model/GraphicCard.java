package com.onliner.tocks.model;


import com.onliner.tocks.model.additional.GraphicCardAdditionalInformation;
import com.onliner.tocks.parsing.common.product.Product;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "graphic-cards")
@ToString(callSuper = true)
public class GraphicCard extends Product {
    private GraphicCardAdditionalInformation additional;
}

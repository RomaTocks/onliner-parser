package com.onliner.tocks.model.product;


import com.onliner.tocks.model.product.additional.GraphicCardAdditionalInformation;
import com.onliner.tocks.model.product.additional.values.GraphicCardAdditionalValues;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "graphic-cards")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(callSuper = true)
public class GraphicCard extends Product
{
    private GraphicCardAdditionalInformation additional;
    private GraphicCardAdditionalValues values;
}

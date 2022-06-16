package com.onliner.tocks.model.product;

import com.onliner.tocks.model.product.additional.SSDAdditionalInformation;
import com.onliner.tocks.model.product.additional.values.SSDAdditionalValues;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ram")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SSD extends Product
{
    private SSDAdditionalInformation additional;
    private SSDAdditionalValues values;
}

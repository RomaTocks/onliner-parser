package com.onliner.tocks.model.product;

import com.onliner.tocks.model.product.additional.FanAdditionalInformation;
import com.onliner.tocks.model.product.additional.values.FanAdditionalValues;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fan")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Fan extends Product
{
    private FanAdditionalInformation additional;
    private FanAdditionalValues values;
}

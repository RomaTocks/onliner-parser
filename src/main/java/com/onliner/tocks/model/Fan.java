package com.onliner.tocks.model;

import com.onliner.tocks.model.additional.FanAdditionalInformation;
import com.onliner.tocks.model.additional.values.FanAdditionalValues;
import com.onliner.tocks.parsing.common.product.Product;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "fan")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Fan extends Product {
    private FanAdditionalInformation additional;
    private FanAdditionalValues values;
}

package com.onliner.tocks.model.product;

import com.onliner.tocks.model.product.additional.RamAdditionalInformation;
import com.onliner.tocks.model.product.additional.values.RamAdditionalValues;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ram")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Ram extends Product
{
    private RamAdditionalInformation additional;
    private RamAdditionalValues values;
}

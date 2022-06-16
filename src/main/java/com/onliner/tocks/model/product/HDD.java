package com.onliner.tocks.model.product;

import com.onliner.tocks.model.product.additional.HDDAdditionalInformation;
import com.onliner.tocks.model.product.additional.values.HDDAdditionalValues;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hdd")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HDD extends Product
{
    private HDDAdditionalInformation additional;
    private HDDAdditionalValues values;
}

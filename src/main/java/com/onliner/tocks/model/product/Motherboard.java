package com.onliner.tocks.model.product;

import com.onliner.tocks.model.product.additional.MotherboardAdditionalInformation;
import com.onliner.tocks.model.product.additional.values.MotherboardAdditionalValues;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "motherboard")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Motherboard extends Product
{
    private MotherboardAdditionalInformation additional;
    private MotherboardAdditionalValues values;
}

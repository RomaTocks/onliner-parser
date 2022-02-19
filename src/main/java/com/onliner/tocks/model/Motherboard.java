package com.onliner.tocks.model;

import com.onliner.tocks.model.additional.MotherboardAdditionalInformation;
import com.onliner.tocks.model.additional.values.MotherboardAdditionalValues;
import com.onliner.tocks.parsing.common.product.Product;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "motherboard")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Motherboard extends Product {
    private MotherboardAdditionalInformation additional;
    private MotherboardAdditionalValues values;
}

package com.onliner.tocks.model;

import com.onliner.tocks.model.additional.CPUAdditionalInformation;
import com.onliner.tocks.model.additional.values.CPUAdditionalValues;
import com.onliner.tocks.parsing.common.product.Product;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "processors")
@ToString(callSuper = true)
public class CPU extends Product {
    private CPUAdditionalInformation additional;
    private CPUAdditionalValues values;
}

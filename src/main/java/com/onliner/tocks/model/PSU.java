package com.onliner.tocks.model;

import com.onliner.tocks.model.additional.PSUAdditionalInformation;
import com.onliner.tocks.parsing.common.product.Product;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "psu")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PSU extends Product {
    private PSUAdditionalInformation additional;
}

package com.onliner.tocks.model;

import com.onliner.tocks.model.additional.SSDAdditionalInformation;
import com.onliner.tocks.parsing.common.Product;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ssd")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SSD extends Product {
    private SSDAdditionalInformation additional;
}

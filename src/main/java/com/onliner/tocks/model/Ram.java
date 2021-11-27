package com.onliner.tocks.model;

import com.onliner.tocks.model.additional.RamAdditionalInformation;
import com.onliner.tocks.parsing.common.Product;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ram")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Ram extends Product {
    private RamAdditionalInformation additional;
}

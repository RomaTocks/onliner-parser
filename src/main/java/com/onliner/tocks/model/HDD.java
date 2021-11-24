package com.onliner.tocks.model;


import com.onliner.tocks.model.additional.HDDAdditionalInformation;
import com.onliner.tocks.parsing.common.Product;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "hdd")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class HDD extends Product {
    private HDDAdditionalInformation additional;
}

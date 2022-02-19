package com.onliner.tocks.model;

import com.onliner.tocks.model.additional.ChassisAdditionalInformation;
import com.onliner.tocks.model.additional.values.ChassisAdditionalValues;
import com.onliner.tocks.parsing.common.product.Product;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "chassis")
@ToString(callSuper = true)
public class Chassis extends Product
{
    private ChassisAdditionalInformation additional;
    private ChassisAdditionalValues values;
}

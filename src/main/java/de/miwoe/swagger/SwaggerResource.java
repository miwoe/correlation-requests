package de.miwoe.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by mwoelm on 19.09.17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SwaggerResource {


//    @ApiParam(required = true)
    @NotNull
    String a;

    String b;


    SwaggerRelationResource swaggerRelationResource;

}

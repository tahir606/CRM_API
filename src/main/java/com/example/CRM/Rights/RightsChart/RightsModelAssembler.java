package com.example.CRM.Rights.RightsChart;

import com.example.CRM.Rights.RightsListController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RightsModelAssembler implements RepresentationModelAssembler<RightChart, EntityModel<RightChart>> {
    @Override
    public EntityModel<RightChart> toModel(RightChart entity) {
        return EntityModel.of(entity, //
                WebMvcLinkBuilder.linkTo(methodOn(RightController.class).one( entity.getRightsChartId())).withSelfRel(),
                linkTo(methodOn(RightController.class).all()).withRel("rights_chart"));
    }
}

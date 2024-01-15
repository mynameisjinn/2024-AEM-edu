/*
 *  Copyright 2015 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.nsc.core.models;

import com.google.common.collect.Lists;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.*;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BootstrapModel {

    @ChildResource
    private List<Item> multifield;

    @Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
            defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    public static class Item {
        @ValueMapValue
        private String image;
        @ValueMapValue
        private String label;
        @ValueMapValue
        private String description;

        public String getImage() {
            return image;
        }

        public String getLabel() {
            return label;
        }

        public String getDescription() {
            return description;
        }
    }


    public List<Item> getMultifield() {
        return multifield;
    }
}